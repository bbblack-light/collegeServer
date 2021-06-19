package com.example.collegeServer.controllers;

import com.example.collegeServer.dto.ConsultingDto;
import com.example.collegeServer.services.ConsultingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consulting")
public class ConsultingController {
    private final ConsultingService consultingService;

    public ConsultingController(ConsultingService consultingService) {
        this.consultingService = consultingService;
    }

    @PostMapping("/")
    public ConsultingDto update(@RequestBody ConsultingDto dto) {
        return consultingService.save(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        return consultingService.deleteById(id);
    }

    @DeleteMapping("/{id}/{userId}")
    public ResponseEntity deleteJoin(@PathVariable("id") Long id, @PathVariable("userId") String userId) {
        return consultingService.deleteJoin(id, userId);
    }

    @GetMapping("/{id}")
    public ConsultingDto get(@PathVariable("id") Long id) {
        return consultingService.getById(id);
    }

    @GetMapping("/")
    public List<ConsultingDto> getAll() {
        return consultingService.getAll();
    }

    @GetMapping("/{id}/{userId}")
    public ResponseEntity addJoin(@PathVariable("id") Long id, @PathVariable("userId") String userId) {
        return consultingService.addJoin(id, userId);
    }
}
