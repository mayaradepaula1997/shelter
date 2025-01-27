package com.dev.abrigo.service;

import com.dev.abrigo.entites.Animal;
import com.dev.abrigo.entites.Treatment;
import com.dev.abrigo.entites.dto.treatment.CreateTreatment;
import com.dev.abrigo.entites.dto.treatment.FinishedTreatment;
import com.dev.abrigo.entites.dto.treatment.UpdateTreatment;
import com.dev.abrigo.repository.AnimalRepository;
import com.dev.abrigo.repository.TreatmentRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class TreatmentService {


    private TreatmentRepository treatmentRepository;

    private AnimalRepository animalRepository;


    public TreatmentService(TreatmentRepository treatmentRepository,AnimalRepository animalRepository) {  //injeção de dependencia via construtor
        this.treatmentRepository = treatmentRepository;
        this.animalRepository = animalRepository;
    }

    //CRIAR

    public Treatment insert(CreateTreatment createTreatment){

        Treatment treatment = new Treatment();

        treatment.setName(createTreatment.name());
        treatment.setStartDate(createTreatment.startDate());
        treatment.setTreatmentStatus(createTreatment.treatmentStatus());

        Optional<Animal> optionalAnimal = animalRepository.findById(createTreatment.animalId());  //Optionnal: indica a presença ou ausência de um valor.

        Animal animal; //declarada uma variável do tipo Animal que mais tarde irá armazenar o animal encontrado no repositório

        if (optionalAnimal.isPresent()){

            animal = optionalAnimal.get(); //Se o Optional contiver um valor, você obtém esse valor e o atribui à variável animal


        }else{

            throw new RuntimeException("Animal not found");
        }

        treatment.setAnimal(animal);

        return treatmentRepository.save(treatment);

    }


    //LISTAR TODOS

    public List<Treatment> findAll(){

        return treatmentRepository.findAll();
    }


    //LISTA PELO ID

    public Optional<Treatment> findById (Long id){

        return treatmentRepository.findById(id);
    }

    //ATUALIAZAR

    public Treatment update (Long id, UpdateTreatment updateTreatment){

        Optional<Treatment> optionalTreatment = treatmentRepository.findById(id);

        if (optionalTreatment.isPresent()){

            Treatment treatment = optionalTreatment.get();

            treatment.setName(updateTreatment.name());
            treatment.setStartDate(updateTreatment.startDate());
            treatment.setTreatmentStatus(updateTreatment.treatmentStatus());


            return treatmentRepository.save(treatment);

        }else{

           throw new RuntimeException("Treatment not found");
        }

    }

    //ATUALIZAÇÃO DO STATUS E DTA FINAL DO TRATAMENTO

    public Treatment finishedTreatment(Long id, FinishedTreatment finishedTreatment){

        Optional<Treatment> optionalTreatment = treatmentRepository.findById(id);

        if (optionalTreatment.isPresent()){

            Treatment treatment = optionalTreatment.get();

            treatment.setEndDate(finishedTreatment.endDate());
            treatment.setTreatmentStatus(finishedTreatment.treatmentStatus());

            return treatmentRepository.save(treatment);


        }else{

            throw  new RuntimeException("Treatment not found");
        }

    }



    //DELETAR
        public void delete(Long id){

        Optional<Treatment> optionalTreatment = treatmentRepository.findById(id);

        if (optionalTreatment.isEmpty()){

            throw new RuntimeException("Treatment not found");

        }else{

            treatmentRepository.deleteById(id);
        }
    }

}


