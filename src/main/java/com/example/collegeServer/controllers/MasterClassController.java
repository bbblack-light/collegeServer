package com.example.collegeServer.controllers;

import com.example.collegeServer.dto.ConsultingDto;
import com.example.collegeServer.dto.MasterClassDto;
import com.example.collegeServer.services.MasterClassService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consulting")
public class MasterClassController {
    private final MasterClassService masterClassService;

    public MasterClassController(MasterClassService masterClassService) {
        this.masterClassService = masterClassService;
    }

    @PostMapping("/")
    public MasterClassDto update(@RequestBody MasterClassDto dto) {
        return masterClassService.save(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        return masterClassService.deleteById(id);
    }

    @GetMapping("/{id}")
    public MasterClassDto get(@PathVariable("id") Long id) {
        return masterClassService.getById(id);
    }

    @DeleteMapping("/{id}/{userId}")
    public ResponseEntity deleteJoin(@PathVariable("id") Long id, @PathVariable("userId") String userId) {
        return masterClassService.deleteJoin(id, userId);
    }

    @PostMapping("/{id}")
    public ResponseEntity addJoin(@PathVariable("id") Long id, @PathVariable("userId") String userId) {
        return masterClassService.addJoin(id, userId);
    }

}
