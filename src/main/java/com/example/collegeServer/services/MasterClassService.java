package com.example.collegeServer.services;

import com.example.collegeServer.controllers.utils.exception.InvalidArgumentException;
import com.example.collegeServer.controllers.utils.exception.NotFoundException;
import com.example.collegeServer.dto.MasterClassDto;
import com.example.collegeServer.model.buisness.Consulting;
import com.example.collegeServer.model.buisness.JoinMasterClassUser;
import com.example.collegeServer.model.buisness.MasterClass;
import com.example.collegeServer.model.user.User;
import com.example.collegeServer.repo.JoinMasterClassUserRepo;
import com.example.collegeServer.repo.MasterClassRepo;
import com.example.collegeServer.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MasterClassService {
    private final ModelMapper modelMapper;
    private final MasterClassRepo masterClassRepo;
    private final JoinMasterClassUserRepo joinMasterClassUserRepo;
    private final UserRepo userRepo;

    public MasterClassService(ModelMapper modelMapper, MasterClassRepo masterClassRepo, JoinMasterClassUserRepo joinMasterClassUserRepo, UserRepo userRepo) {
        this.modelMapper = modelMapper;
        this.masterClassRepo = masterClassRepo;
        this.joinMasterClassUserRepo = joinMasterClassUserRepo;
        this.userRepo = userRepo;
    }
    public MasterClassDto save(MasterClassDto dto) {
        if (Objects.isNull(dto)) {
            throw new InvalidArgumentException("Мастер класс не должен быть null");
        }
        MasterClass consulting = modelMapper.map(dto, MasterClass.class);
        return modelMapper.map(masterClassRepo.save(consulting), MasterClassDto.class);
    }

    public MasterClassDto getById(Long id) {
        if (masterClassRepo.existsById(id)) {
            return  modelMapper.map(masterClassRepo.getById(id), MasterClassDto.class);
        }
        throw new NotFoundException("Мастер класс с id " + id + " не найдена!");
    }

    public ResponseEntity deleteById(Long id) {
        //todo: mail sender
        if (masterClassRepo.existsById(id)) {
            masterClassRepo.deleteById(id);
            return ResponseEntity.ok().build();
        }
        throw new NotFoundException("Мастер класс с id " + id + " не найдена!");
    }

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
}
