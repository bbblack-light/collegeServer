package com.example.collegeServer.services;

import com.example.collegeServer.controllers.utils.exception.InvalidArgumentException;
import com.example.collegeServer.controllers.utils.exception.NotFoundException;
import com.example.collegeServer.dto.MasterClassDto;
import com.example.collegeServer.model.buisness.JoinMasterClassUser;
import com.example.collegeServer.model.buisness.MasterClass;
import com.example.collegeServer.model.user.User;
import com.example.collegeServer.repo.JoinMasterClassUserRepo;
import com.example.collegeServer.repo.MasterClassRepo;
import com.example.collegeServer.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class MasterClassService {

    private static Logger logger = LoggerFactory.getLogger(MasterClassService.class);
    private final EmailService emailService;
    private final ModelMapper modelMapper;
    private final MasterClassRepo masterClassRepo;
    private final JoinMasterClassUserRepo joinMasterClassUserRepo;
    private final UserRepo userRepo;

    public MasterClassService(EmailService emailService, ModelMapper modelMapper, MasterClassRepo masterClassRepo, JoinMasterClassUserRepo joinMasterClassUserRepo, UserRepo userRepo) {
        this.emailService = emailService;
        this.modelMapper = modelMapper;
        this.masterClassRepo = masterClassRepo;
        this.joinMasterClassUserRepo = joinMasterClassUserRepo;
        this.userRepo = userRepo;
    }
    public MasterClassDto save(MasterClassDto dto) {
        if (Objects.isNull(dto)) {
            throw new InvalidArgumentException("Мастер класс не должен быть null");
        }
        MasterClass masterClass = modelMapper.map(dto, MasterClass.class);
        MasterClass buff  = masterClassRepo.findById(dto.getId()).orElse(null);
        List<JoinMasterClassUser> users = buff!=null ? buff.getUsers().stream().collect(Collectors.toList()) : new ArrayList<>();
        Date date = null;
        if (buff!=null) {
            date = (Date) buff.getDate().clone();
        }
        masterClass = masterClassRepo.save(masterClass);
        if (date!=null && masterClass.getDate()!=null && !masterClass.getDate().equals(date)) {
            MasterClass finalMasterClass = masterClass;
            Date finalDate = date;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy. 'в' HH:mm");
            users.forEach(user -> {
                if (user.getUser()!=null && user.getUser().getEmail()!=null && !user.getUser().getEmail().isEmpty()) {
                    try {
                        this.emailService.sendSimpleMessage(user.getUser().getEmail(), "Перенос мастер-класса",
                                "Мастер класс по теме " + finalMasterClass.getTitle() +
                                        " у спикера " + finalMasterClass.getSpeakerName()  +
                                        dateFormat.format(finalDate) + " был перенен на " +dateFormat.format(finalMasterClass.getDate()));
                    }
                    catch (Exception e) {
                        logger.info(e.getMessage());
                    }
                }
            });
        }

        return modelMapper.map(masterClass, MasterClassDto.class);
    }

    public MasterClassDto getById(Long id) {
        if (masterClassRepo.existsById(id)) {
            return  modelMapper.map(masterClassRepo.getById(id), MasterClassDto.class);
        }
        throw new NotFoundException("Мастер класс с id " + id + " не найдена!");
    }

    public ResponseEntity deleteById(Long id) {
        MasterClass masterClass = masterClassRepo.findById(id).orElse(null);
        //todo: mail sender
        if (masterClass!=null) {
            masterClassRepo.deleteById(id);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy. 'в' HH:mm");
            masterClass.getUsers().forEach(user -> {
                if (user.getUser()!=null && user.getUser().getEmail()!=null && !user.getUser().getEmail().isEmpty()) {
                    try {
                        this.emailService.sendSimpleMessage(user.getUser().getEmail(), "Отмена мастер-кдасса",
                                "Мастер класс по теме " + masterClass.getTitle() +
                                        " у спикера " + masterClass.getSpeakerName()  +
                                        dateFormat.format(masterClass.getDate()) + " был отменен");
                    } catch (Exception e) {
                        logger.info(e.getMessage());
                    }
                }
            });
            return ResponseEntity.ok().build();
        }
        throw new NotFoundException("Мастер класс с id " + id + " не найдена!");
    }

    @Transactional
    public ResponseEntity deleteJoin(Long id, String userId) {
        User user = userRepo.findOneByUserId(userId).orElseThrow(() -> new NotFoundException("Пользователь с userId " + userId + " не найден!"));
        MasterClass masterClass = masterClassRepo.findById(id).orElseThrow(() -> new NotFoundException("Мастер класс с id " + id + " не найден!"));
        if (joinMasterClassUserRepo.existsByUserAndMasterClass(user, masterClass)) {
            joinMasterClassUserRepo.deleteByUserAndMasterClass(user, masterClass);
            return ResponseEntity.ok().build();
        }
        throw new NotFoundException("Запись не найдена!");
    }

    public ResponseEntity addJoin(Long id, String userId) {
        User user = userRepo.findOneByUserId(userId).orElseThrow(() -> new NotFoundException("Пользователь с userId " + userId + " не найден!"));
        MasterClass masterClass = masterClassRepo.findById(id).orElseThrow(() -> new NotFoundException("Мастер класс с id " + id + " не найден!"));
        if (!joinMasterClassUserRepo.existsByUserAndMasterClass(user, masterClass)) {
            JoinMasterClassUser join = new JoinMasterClassUser();
            join.setMasterClass(masterClass);
            join.setUser(user);
            joinMasterClassUserRepo.save(join);
        }
        return ResponseEntity.ok().build();
    }

    public List<MasterClassDto> getAll() {
        return masterClassRepo.findAll().stream()
                .map(page-> modelMapper.map(page, MasterClassDto.class))
                .collect(Collectors.toList());
    }
}
