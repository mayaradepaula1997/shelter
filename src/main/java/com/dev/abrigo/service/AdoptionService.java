package com.dev.abrigo.service;


import com.dev.abrigo.entites.Adoption;
import com.dev.abrigo.entites.Animal;
import com.dev.abrigo.entites.dto.adoption.CreateAdoption;
import com.dev.abrigo.entites.dto.adoption.UpdateAdoption;
import com.dev.abrigo.exception.Exception;
import com.dev.abrigo.repository.AdoptionRepository;
import com.dev.abrigo.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdoptionService {

    private AdoptionRepository adoptionRepository;

    private AnimalRepository animalRepository;

    public AdoptionService(AdoptionRepository adoptionRepository, AnimalRepository animalRepository) {
        this.adoptionRepository = adoptionRepository;
        this.animalRepository = animalRepository;
    }

    //CRIAR

    public Adoption insert (CreateAdoption createAdoption){

        Adoption adoption = new Adoption();

        adoption.setAdoptionDate(createAdoption.adoptionDate());
        adoption.setAdoptedName(createAdoption.adoptedName());
        adoption.setCpfAdopte(createAdoption.cpfAdopte());


        Optional<Animal> optionalAnimal = animalRepository.findById(createAdoption.animalId());

        Animal animal = null;  //Declara a variável "animal" para armazenar o valor que será obtido do Optional

        if (optionalAnimal.isPresent()){

            animal = optionalAnimal.get(); //Se o Optional tiver um valor, você obtém esse valor e o atribui à variável animal

        }

        if (optionalAnimal.isEmpty()){

         throw new RuntimeException("Animal not found");
        }

        adoption.setAnimal(animal);

        return adoptionRepository.save(adoption);


    }

    //LISTAR TODOS

    public List<Adoption> adoptionList(){

        return adoptionRepository.findAll();
    }

    //LISTAR PELO ID

    public Optional<Adoption> findById(Long id){  //Optional para verificar se id passado no argumento existe

        return adoptionRepository.findById(id); //chama o repository e passa o id
    }

    //ATULIAZAR

    public Adoption update(Long id, UpdateAdoption updateAdoption){

        Optional<Adoption> optionalAdoption = adoptionRepository.findById(id);

        if (optionalAdoption.isPresent()){

            Adoption adoption = optionalAdoption.get(); //O método get() é utilizado para obter o valor armazenado dentro do Optional(se esta vazio ou não)

            adoption.setAdoptionDate(updateAdoption.adoptionDate());
            adoption.setAdoptedName(updateAdoption.adoptedName());
            adoption.setCpfAdopte(updateAdoption.cpfAdopte());

            return adoptionRepository.save(adoption);
        }

        else throw new Exception("Animal not found");

    }

    //DELETAR

    public void delete (Long id){

        adoptionRepository.findById(id);

    }


}
