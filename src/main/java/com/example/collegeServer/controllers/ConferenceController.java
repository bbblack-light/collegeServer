package com.example.collegeServer.controllers;

import com.example.collegeServer.dto.ConferenceDto;
import com.example.collegeServer.services.ConferenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consulting")
public class ConferenceController {
    private final ConferenceService conferenceService;

    public ConferenceController(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @PostMapping("/")
    public ConferenceDto update(@RequestBody ConferenceDto dto) {
        return conferenceService.save(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        return conferenceService.deleteById(id);
    }

    @GetMapping("/{id}")
    public ConferenceDto get(@PathVariable("id") Long id) {
        return conferenceService.getById(id);
    }

}
