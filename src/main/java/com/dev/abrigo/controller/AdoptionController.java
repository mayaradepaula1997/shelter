package com.dev.abrigo.controller;

import com.dev.abrigo.entites.Adoption;
import com.dev.abrigo.entites.Animal;
import com.dev.abrigo.entites.dto.adoption.CreateAdoption;
import com.dev.abrigo.entites.dto.adoption.UpdateAdoption;
import com.dev.abrigo.service.AdoptionService;
import com.dev.abrigo.service.AnimalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/adoption")
public class AdoptionController {


    private AdoptionService adoptionService;

    private AnimalService animalService;

    //Construtor

    public AdoptionController(AdoptionService adoptionService, AnimalService animalService) {
        this.adoptionService = adoptionService;
        this.animalService = animalService;
    }

    //CRIAR

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody CreateAdoption createAdoption) {

        Optional<Animal> optionalAnimal = animalService.findById(createAdoption.animalId()); //Verificar se id do nimal existe

        if (optionalAnimal.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal not found"); //Se não existe lança exeção
        }

        Adoption adoption = adoptionService.insert(createAdoption); //Se existe instancia a classe Adoption

        return ResponseEntity.ok(adoption);

    }

    //LISTAR TODOS

    @GetMapping
    public ResponseEntity<List<Adoption>> findAll() {

        //chama o serviço "adoptionService" para buscar a lista de adoções
        List<Adoption> adoptionList = adoptionService.adoptionList();

        return ResponseEntity.ok().body(adoptionList);
    }


    //LISTAR PELO ID

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {

        if (id == null || id <= 0) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Optional<Adoption> optionalAdoption = adoptionService.findById(id);

        if (optionalAdoption.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Adoption  not found");

        }
        return ResponseEntity.ok(optionalAdoption);

    }

    //ATUALIZAR

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UpdateAdoption updateAdoption){

        if (id == null || id <= 0){

             return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); //Retorna um erro 400 se o id for inválido

        }

        Optional<Adoption> optionalAdoption = adoptionService.findById(id);

        if (optionalAdoption.isEmpty()){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Adoption  not found");

        }

        Adoption adoption = adoptionService.update(id, updateAdoption);

        return ResponseEntity.ok(adoption);

    }

    //DELETAR
    @DeleteMapping (value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        if (id == null || id <= 0){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }

        Optional<Adoption> optionalAdoption = adoptionService.findById(id);

        if (optionalAdoption.isEmpty()){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Adoption  not found");
        }

        adoptionService.delete(id);

        return ResponseEntity.noContent().build();

    }

}

