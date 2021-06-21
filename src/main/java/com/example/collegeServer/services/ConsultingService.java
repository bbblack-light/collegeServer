package com.example.collegeServer.services;

import com.example.collegeServer.controllers.utils.exception.InvalidArgumentException;
import com.example.collegeServer.controllers.utils.exception.NotFoundException;
import com.example.collegeServer.dto.ConsultingDto;
import com.example.collegeServer.model.buisness.Consulting;
import com.example.collegeServer.model.buisness.JoinConsultingUser;
import com.example.collegeServer.model.user.User;
import com.example.collegeServer.repo.ConsultingRepo;
import com.example.collegeServer.repo.JoinConsultingUserRepo;
import com.example.collegeServer.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ConsultingService {
    private static Logger logger = LoggerFactory.getLogger(ConsultingService.class);
    private final EmailService emailService;
    private final ModelMapper modelMapper;
    private final ConsultingRepo consultingRepo;
    private final JoinConsultingUserRepo joinConsultingUserRepo;
    private final UserRepo userRepo;

    public ConsultingService(EmailService emailService, ModelMapper modelMapper, ConsultingRepo consultingRepo, JoinConsultingUserRepo joinConsultingUserRepo, UserRepo userRepo) {
        this.emailService = emailService;
        this.modelMapper = modelMapper;
        this.consultingRepo = consultingRepo;
        this.joinConsultingUserRepo = joinConsultingUserRepo;
        this.userRepo = userRepo;
    }

    public ConsultingDto save(ConsultingDto dto) {
        if (Objects.isNull(dto)) {
            throw new InvalidArgumentException("Концультация не должна быть null");
        }
        Consulting consulting = modelMapper.map(dto, Consulting.class);
        Consulting buff  = consultingRepo.findById(dto.getId()).orElse(null);
        List<JoinConsultingUser> users = buff!=null ? buff.getUsers().stream().collect(Collectors.toList()) : new ArrayList<>();
        Date date = null;
        if (buff!=null) {
            date = (Date) buff.getDate().clone();
        }
        consulting = consultingRepo.save(consulting);
        if (date!=null && consulting.getDate()!=null && !consulting.getDate().equals(date)) {
            Consulting finalConsulting = consulting;
            Date finalDate = date;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy. 'в' HH:mm");
            users.forEach(user -> {
                if (user.getUser()!=null && user.getUser().getEmail()!=null && !user.getUser().getEmail().isEmpty()) {
                    try {
                        this.emailService.sendSimpleMessage(user.getUser().getEmail(), "Перенос консультации",
                                "Консультация по предмету " + finalConsulting.getDiscipline() +
                                        " у преподователя " + finalConsulting.getTeacherName()  +
                                        " " + dateFormat.format(finalDate) + " был перенен на " +dateFormat.format(finalConsulting.getDate()));
                    }
                    catch (Exception e) {
                        logger.info(e.getMessage());
                    }
                }
            });
        }

        return modelMapper.map(consulting, ConsultingDto.class);
    }

    public ConsultingDto getById(Long id) {
        if (consultingRepo.existsById(id)) {
            return modelMapper.map(consultingRepo.getById(id), ConsultingDto.class);
        }
        throw new NotFoundException("Консультация с id " + id + " не найдена!");
    }

    public ResponseEntity deleteById(Long id) {
        Consulting consulting = consultingRepo.findById(id).orElse(null);
        if (consulting!=null) {
            consultingRepo.deleteById(id);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy. 'в' HH:mm");
            consulting.getUsers().forEach(user -> {
                if (user.getUser()!=null && user.getUser().getEmail()!=null && !user.getUser().getEmail().isEmpty()) {
                    try {
                        this.emailService.sendSimpleMessage(user.getUser().getEmail(), "Отмена консультации",
                           "Консультация по предмету " + consulting.getDiscipline() +
                                " у преподователя " + consulting.getTeacherName()  + dateFormat.format(consulting.getDate()) + " была отменена");
                    }
                    catch (Exception e) {
                        logger.info(e.getMessage());
                    }
                }
            });
            return ResponseEntity.ok().build();
        }
        throw new NotFoundException("Консультация с id " + id + " не найдена!");
    }

    @Transactional
    public ResponseEntity deleteJoin(Long id, String userId) {
        User user = userRepo.findOneByUserId(userId).orElseThrow(() -> new NotFoundException("Пользователь с userId " + userId + " не найден!"));
        Consulting consulting = consultingRepo.findById(id).orElseThrow(() -> new NotFoundException("Консультация с id " + id + " не найдена!"));
        if (joinConsultingUserRepo.existsByUserAndConsulting(user, consulting)) {
            joinConsultingUserRepo.deleteByUserAndConsulting(user, consulting);
            return ResponseEntity.ok().build();
        }
        throw new NotFoundException("Запись не найдена!");
    }

    public ResponseEntity addJoin(Long id, String userId) {
        User user = userRepo.findOneByUserId(userId).orElseThrow(() -> new NotFoundException("Пользователь с userId " + userId + " не найден!"));
        Consulting consulting = consultingRepo.findById(id).orElseThrow(() -> new NotFoundException("Консультация с id " + id + " не найдена!"));
        if (!joinConsultingUserRepo.existsByUserAndConsulting(user, consulting)) {
            JoinConsultingUser join = new JoinConsultingUser();
            join.setConsulting(consulting);
            join.setUser(user);
            joinConsultingUserRepo.save(join);
        }
        return ResponseEntity.ok().build();
    }

    public List<ConsultingDto> getAll() {
        return consultingRepo.findAll().stream()
                .map(page-> modelMapper.map(page, ConsultingDto.class))
                .collect(Collectors.toList());
    }
}
