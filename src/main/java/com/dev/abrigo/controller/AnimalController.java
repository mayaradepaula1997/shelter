package com.dev.abrigo.controller;

import com.dev.abrigo.entites.Animal;
import com.dev.abrigo.entites.Shelter;
import com.dev.abrigo.entites.Vaccine;
import com.dev.abrigo.entites.dto.animal.CreateAnimal;
import com.dev.abrigo.entites.dto.animal.UpdateAnimal;
import com.dev.abrigo.service.AnimalService;
import com.dev.abrigo.service.ShelterService;
import com.dev.abrigo.service.VaccineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/animal")
public class AnimalController {

    private final AnimalService animalService;

    private final ShelterService shelterService;

    private final VaccineService vaccineService;


    //Construtor

    public AnimalController(AnimalService animalService, ShelterService shelterService, VaccineService vaccineService) {
        this.animalService = animalService;
        this.shelterService = shelterService;
        this.vaccineService = vaccineService;
    }




    @PostMapping
    public ResponseEntity<?> insert(@RequestBody CreateAnimal createAnimal) {

        Optional<Shelter> optionalShelter = shelterService.findById(createAnimal.shelterId());

        if(optionalShelter.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shelter not found");
        }

        Animal newAnimal = animalService.insert(createAnimal);

        return ResponseEntity.ok(newAnimal);
    }



    @GetMapping
    public ResponseEntity<List<Animal>> findAll() {

        List<Animal> list = animalService.listarTodos();

        return ResponseEntity.ok().body(list);

    }



    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {

        if (id == null || id <= 0) {

            return ResponseEntity.badRequest().build();

        }
        Optional<Animal> optionalAnimal = animalService.findById(id);

        if (optionalAnimal.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal is not found");

        }

        return ResponseEntity.ok(optionalAnimal);

    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UpdateAnimal updateAnimal) {

        if (id == null || id <= 0) {

            return ResponseEntity.badRequest().build(); //Retorna um erro 400 se o id for inválido.
        }

        Optional<Animal> optionalAnimal = animalService.findById(id);

        if (optionalAnimal.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shelter is not found");
        }

        Animal animal = animalService.update(id, updateAnimal);

        return ResponseEntity.ok(animal);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        if (id == null || id <= 0) {

            return ResponseEntity.badRequest().build();

        }

        Optional<Animal> optionalAnimal = animalService.findById(id);

        if (optionalAnimal.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(optionalAnimal);
        }

        // Chamar o serviço para deletar o recurso
        animalService.delete(id);

        return ResponseEntity.noContent().build();

    }


    //ATUALIZAR VACINA

    @PutMapping(value = "/{id}/vaccine")
    public ResponseEntity<?> addVacinne
            (@PathVariable Long id,  @RequestBody Long idVaccine) {

        if (id == null || id <= 0 && idVaccine == null || idVaccine <= 0) {

            return ResponseEntity.badRequest().build(); //Retorna um erro 400 se o id for inválido.

        }

        Optional<Animal> optionalAnimal = animalService.findById(id);

        Optional<Vaccine> optionalVaccine = vaccineService.findById(idVaccine);
        if (optionalAnimal.isEmpty()) {


            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal is not found");
        }

        if (optionalVaccine.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaccine is not found");

        }

        Animal updateAnimal = animalService.updateVaccine(id, idVaccine);

        return ResponseEntity.ok(updateAnimal);


    }
}



