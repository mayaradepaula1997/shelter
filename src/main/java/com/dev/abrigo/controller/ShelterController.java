package com.dev.abrigo.controller;

import com.dev.abrigo.entites.Shelter;
import com.dev.abrigo.entites.dto.CreateShelter;
import com.dev.abrigo.service.ShelterService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/shelter")
public class ShelterController {


    private ShelterService shelterService;

    public ShelterController(ShelterService shelterService) {
        this.shelterService = shelterService;
    }

    @PostMapping
    public ResponseEntity<Shelter> insert(@RequestBody CreateShelter createShelter){

        Shelter newShelter = shelterService.insert(createShelter);

        return ResponseEntity.ok().body(newShelter);

    }

}
