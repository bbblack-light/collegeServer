package com.example.collegeServer.services;

import com.example.collegeServer.controllers.utils.exception.InvalidArgumentException;
import com.example.collegeServer.controllers.utils.exception.NotFoundException;
import com.example.collegeServer.dto.ConsultingDto;
import com.example.collegeServer.model.buisness.Consulting;
import com.example.collegeServer.model.buisness.JoinConsultingUser;
import com.example.collegeServer.model.buisness.JoinMasterClassUser;
import com.example.collegeServer.model.buisness.MasterClass;
import com.example.collegeServer.model.user.User;
import com.example.collegeServer.repo.ConsultingRepo;
import com.example.collegeServer.repo.JoinConsultingUserRepo;
import com.example.collegeServer.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ConsultingService {

    private final ModelMapper modelMapper;
    private final ConsultingRepo consultingRepo;
    private final JoinConsultingUserRepo joinConsultingUserRepo;
    private final UserRepo userRepo;

    public ConsultingService(ModelMapper modelMapper, ConsultingRepo consultingRepo, JoinConsultingUserRepo joinConsultingUserRepo, UserRepo userRepo) {
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
        return modelMapper.map(consultingRepo.save(consulting), ConsultingDto.class);
    }

    public ConsultingDto getById(Long id) {
        if (consultingRepo.existsById(id)) {
            return  modelMapper.map(consultingRepo.getById(id), ConsultingDto.class);
        }
        throw new NotFoundException("Консультация с id " + id + " не найдена!");
    }

    public ResponseEntity deleteById(Long id) {
        //todo: mail sender
        if (consultingRepo.existsById(id)) {
            consultingRepo.deleteById(id);
            return ResponseEntity.ok().build();
        }
        throw new NotFoundException("Консультация с id " + id + " не найдена!");
    }

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
}
