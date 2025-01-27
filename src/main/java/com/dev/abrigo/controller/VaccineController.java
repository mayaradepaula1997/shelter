package com.dev.abrigo.controller;

import com.dev.abrigo.entites.Vaccine;
import com.dev.abrigo.entites.dto.vaccine.CreateVacinne;
import com.dev.abrigo.entites.dto.vaccine.UpdateVaccine;
import com.dev.abrigo.service.VaccineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/vacinne")
public class VaccineController {


    private VaccineService vaccineService;

    public VaccineController(VaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    @PostMapping
    public ResponseEntity<Vaccine> insert(@RequestBody CreateVacinne createVacinne){

        Vaccine newvacinne = vaccineService.insert(createVacinne);

        return ResponseEntity.ok().body(newvacinne);
    }

    @GetMapping
    public ResponseEntity<List<Vaccine>> findAll() {   //listagem das vacinas

        List<Vaccine> vaccineList = vaccineService.findAll(); //instanciando a lista de vacina

        return ResponseEntity.ok().body(vaccineList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){

        if (id == null || id <= 0){

        return ResponseEntity.badRequest().build();  //requisição está incorreta

        }

        Optional<Vaccine> optionalVaccine = vaccineService.findById(id);

        if (optionalVaccine.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaccine is not found");
        }

        return ResponseEntity.ok(optionalVaccine);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UpdateVaccine updateVaccine){ //dados que serão usados para atualizar

        if(id == null || id<= 0){

            return ResponseEntity.badRequest().build(); //requisição está incorreta
        }

        Optional<Vaccine> vaccineOptional = vaccineService.findById(id);

        if(vaccineOptional.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaccine is not found");

        }

      Vaccine vaccine = vaccineService.update(id, updateVaccine); //vaccineService:É chamado para atualizar a vacina com os novos dados contidos em updateVaccine

        return ResponseEntity.ok().body(vaccine);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delelte (@PathVariable Long id){

        if (id == null || id<=0){

            return ResponseEntity.badRequest().build();

        }

        Optional<Vaccine> optionalVaccine = vaccineService.findById(id);

        if(optionalVaccine.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaccine is not found");

        }

        vaccineService.delete(id); //o método delete do "vaccineService" é chamado para deletar a vacina com o ID fornecido.

        return ResponseEntity.noContent().build();
    }
}
