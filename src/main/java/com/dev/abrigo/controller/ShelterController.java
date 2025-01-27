package com.dev.abrigo.controller;


import com.dev.abrigo.entites.Shelter;
import com.dev.abrigo.entites.dto.shelter.CreateShelter;
import com.dev.abrigo.entites.dto.shelter.UpdateShelter;
import com.dev.abrigo.service.ShelterService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/shelter")
public class ShelterController {


    private ShelterService shelterService;

    public ShelterController(ShelterService shelterService) { //inejeção de dependencia via construtor
        this.shelterService = shelterService;
    }

    @PostMapping
    public ResponseEntity<Shelter> insert(@RequestBody CreateShelter createShelter){

        Shelter newShelter = shelterService.insert(createShelter);

        return ResponseEntity.ok().body(newShelter);

    }

    @GetMapping  //retorna abrigos por paginação
    public ResponseEntity<Page<Shelter>> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){

        Page<Shelter> shelters = shelterService.findAll(page, size);

        return ResponseEntity.ok(shelters);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){

        if (id == null || id <= 0){

            return ResponseEntity.badRequest().build();
        }

        Optional<Shelter> optionalShelter = shelterService.findById(id);

        if (optionalShelter.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shelter is not found");
        }

       return ResponseEntity.ok(optionalShelter);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UpdateShelter updateShelter){

        if(id == null || id <= 0){

            return ResponseEntity.badRequest().build(); //Retorna um erro 400 se o id for inválido.
        }

        Optional<Shelter> optionalShelter = shelterService.findById(id);

        if (optionalShelter.isEmpty()){  //Verificar se é vazio

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shelter is not found");
        }

        Shelter shelter = shelterService.update(id,updateShelter);

        return ResponseEntity.ok().body(shelter);

    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        if(id == null || id <= 0){

            return ResponseEntity.badRequest().build(); //Retorna um erro 400 se o id for inválido.
        }

        Optional<Shelter> optionalShelter = shelterService.findById(id);

        if(optionalShelter.isEmpty()){ //Verificar se está vazio

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shelter is not found ");

        }

        // Chamar o serviço para deletar o recurso
        shelterService.delete(id);

        return ResponseEntity.noContent().build();

    }

}
