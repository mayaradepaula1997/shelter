package com.dev.abrigo.service;

import com.dev.abrigo.entites.Vaccine;
import com.dev.abrigo.entites.dto.vaccine.CreateVacinne;
import com.dev.abrigo.entites.dto.vaccine.UpdateVaccine;
import com.dev.abrigo.exception.Exception;
import com.dev.abrigo.repository.VaccineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VaccineService {

    private VaccineRepository vaccineRepository;

    public VaccineService(VaccineRepository vaccineRepository) {
        this.vaccineRepository = vaccineRepository;
    }

    //CRIAR

    public Vaccine insert(CreateVacinne createVacinne){

        Vaccine vaccine = new Vaccine(); //instanciar a classe

        vaccine.setName(createVacinne.name());   //setar os valores
        vaccine.setDescription(createVacinne.description());

        return vaccineRepository.save(vaccine);
    }

    //LISTAR

    public List<Vaccine> findAll(){

        return vaccineRepository.findAll();

    }


    //LISTAR PELO ID

    public Optional<Vaccine> findById(Long id){

        return vaccineRepository.findById(id);
    }

    //ATUALIZAR

    public Vaccine update(Long id, UpdateVaccine updateVaccine){

        Optional<Vaccine> optionalVaccine = vaccineRepository.findById(id);  //verificar se o id passado, existe

        if(optionalVaccine.isPresent()){ //se estiver presente, vai setar os valores

            Vaccine vaccine = optionalVaccine.get();

            vaccine.setName(updateVaccine.name());
            vaccine.setDescription(updateVaccine.description());

            return vaccineRepository.save(vaccine);

        }

        throw new Exception("Vaccine not found");

    }

    //DELETAR

    public void delete(Long id){

        Optional<Vaccine> vaccineOptional = vaccineRepository.findById(id);

        if(vaccineOptional.isEmpty()){

            throw new Exception("Vaccine not found");

        }

        vaccineRepository.deleteById(id);

    }
}







