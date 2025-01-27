package com.dev.abrigo.controller;

import com.dev.abrigo.entites.Animal;
import com.dev.abrigo.entites.Treatment;
import com.dev.abrigo.entites.dto.treatment.CreateTreatment;
import com.dev.abrigo.entites.dto.treatment.FinishedTreatment;
import com.dev.abrigo.entites.dto.treatment.UpdateTreatment;
import com.dev.abrigo.service.AnimalService;
import com.dev.abrigo.service.TreatmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/treatment")
public class TreatmentController {

    private TreatmentService treatmentService;

    private AnimalService animalService;

    public TreatmentController(TreatmentService treatmentService, AnimalService animalService) { //injeção de dependencia via construtor
        this.treatmentService = treatmentService;
        this.animalService = animalService;
    }

    //CRIAR
    @PostMapping
    public ResponseEntity<?> insert(@RequestBody CreateTreatment createTreatment){

        System.out.println("Esse é o animal id" + createTreatment.animalId());

        Optional<Animal> optionalAnimal = animalService.findById(createTreatment.animalId());


        if(optionalAnimal.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal not found");
        }

        Treatment treatment = treatmentService.insert(createTreatment);

        return ResponseEntity.ok().body(treatment);

    }


    //LISTAR TODOS
    @GetMapping
    public ResponseEntity<List<Treatment>> findAll(){

        List<Treatment> treatmentList = treatmentService.findAll();

        return ResponseEntity.ok().body(treatmentList);
    }

    //LISTAR ATRAVEZ DO ID

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){

        if ( id == 0 || id < 0){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); //Retorna um erro 400 se o id for inválido.
        }

        Optional<Treatment> optionalTreatment = treatmentService.findById(id);

        if(optionalTreatment.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Treatment is not found");
        }

        return ResponseEntity.ok(optionalTreatment);
    }

    //ATUALIZAR

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UpdateTreatment updateTreatment){

        if (id == 0 || id < 0){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); //Retorna um erro 400 se o id for inválido.

        }

        Optional<Treatment> optionalTreatment = treatmentService.findById(id);

        if (optionalTreatment.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Treatment is not found");

        }else{

            Treatment treatment = treatmentService.update(id,updateTreatment);

            return ResponseEntity.ok().body(treatment);
        }


    }

    //DELETAR
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id){

        if (id == 0 || id < 0){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Optional<Treatment> treatmentOptional = treatmentService.findById(id);

        if (treatmentOptional.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Treatment is not found");

        }

        // Chamar o serviço para deletar o recurso
         treatmentService.delete(id);

         return ResponseEntity.noContent().build();

    }

    //ATUALIZAÇÃO DO STATUS E DTA FINAL DO TRATAMENTO

    @PutMapping(value = "/finished/{id}")
    public ResponseEntity<?> finishedTreatment(@PathVariable Long id, @RequestBody FinishedTreatment finishedTreatment){

        if (id == 0 || id < 0 ){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Optional<Treatment> optionalTreatment = treatmentService.findById(id);

        if(optionalTreatment.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Treatment is not found");


        }

        //Esse if valida se a data final que o usuario esta passando é maior que a data inicial do tratamento
        //isAfter:("É depois?" || "É maior"?): É uma função interna do LocalDate, que compara data do mesmo estilo,
        // comparando se uma data é depois da outra. TIPO: 02/02/2025(FINAL) E 02/03/2025(INICIO)

        if(!finishedTreatment.endDate().isAfter(optionalTreatment.get().getStartDate())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
               .body("The end date must be greater than the start date.");
        }

            Treatment treatment = treatmentService.finishedTreatment(id,finishedTreatment);

            return ResponseEntity.ok().body(treatment);

    }

}
