package com.example.collegeServer.services;

import com.example.collegeServer.controllers.utils.exception.InvalidArgumentException;
import com.example.collegeServer.controllers.utils.exception.NotFoundException;
import com.example.collegeServer.dto.ConferenceDto;
import com.example.collegeServer.dto.ConsultingDto;
import com.example.collegeServer.model.buisness.Conference;
import com.example.collegeServer.model.buisness.Consulting;
import com.example.collegeServer.repo.ConferenceRepo;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ConferenceService {
    private final ModelMapper modelMapper;
    private final ConferenceRepo conferenceRepo;

    public ConferenceService(ModelMapper modelMapper, ConferenceRepo conferenceRepo) {
        this.modelMapper = modelMapper;
        this.conferenceRepo = conferenceRepo;
    }

    public ConferenceDto save(ConferenceDto dto) {
        if (Objects.isNull(dto)) {
            throw new InvalidArgumentException("Концультация не должна быть null");
        }
        Conference conference = modelMapper.map(dto, Conference.class);
        return modelMapper.map(conferenceRepo.save(conference), ConferenceDto.class);
    }

    public ConferenceDto getById(Long id) {
        if (conferenceRepo.existsById(id)) {
            return modelMapper.map(conferenceRepo.getById(id), ConferenceDto.class);
        }
        throw new NotFoundException("Консультация с id " + id + " не найдена!");
    }

    public ResponseEntity deleteById(Long id) {
        //todo: mail sender
        if (conferenceRepo.existsById(id)) {
            conferenceRepo.deleteById(id);
            return ResponseEntity.ok().build();
        }
        throw new NotFoundException("Консультация с id " + id + " не найдена!");
    }
}
