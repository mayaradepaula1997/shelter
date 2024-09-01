package com.dev.abrigo.service;


import com.dev.abrigo.entites.Shelter;
import com.dev.abrigo.entites.dto.CreateShelter;
import com.dev.abrigo.entites.dto.UpdateShelter;
import com.dev.abrigo.exception.Exception;
import com.dev.abrigo.repository.ShelterRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShelterService {

    private ShelterRepository shelterRepository;

    public ShelterService(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }

    //CRIAR

    public Shelter insert(CreateShelter shelterDTO) {

        Shelter newShelter = new Shelter();

        newShelter.setName(shelterDTO.name());
        newShelter.setAddress(shelterDTO.address());
        newShelter.setPhone(shelterDTO.phone());
        newShelter.setEmail(shelterDTO.email());

        return shelterRepository.save(newShelter);

    }

    //LISTAR

    public Page<Shelter> findAll(int page, int size) { //Listar abrigos por paginação
        Pageable pageable = PageRequest.of(page,size);

        return shelterRepository.findAll(pageable);

     //Page<Shelter> shelterPage = shelterRepository.findAll(pageable);
    }

    //LISTAR PELO ID

    public Optional<Shelter> findById(Long id) {

        return shelterRepository.findById(id);

    }

    //ATUALIZAR

    public Shelter update(Long id, UpdateShelter updateShelter) {

        Optional<Shelter> optionalShelter = shelterRepository.findById(id);

        if (optionalShelter.isPresent()) {

            Shelter shelter = optionalShelter.get();

            shelter.setPhone(updateShelter.phone());
            shelter.setEmail(updateShelter.email());

            return shelterRepository.save(shelter);
        }

        throw new Exception("Shelter not found");


    }


}