package com.dev.abrigo.service;
import com.dev.abrigo.entites.Animal;
import com.dev.abrigo.entites.Shelter;
import com.dev.abrigo.entites.Vaccine;
import com.dev.abrigo.entites.VaccineApplied;
import com.dev.abrigo.entites.dto.animal.CreateAnimal;
import com.dev.abrigo.entites.dto.animal.UpdateAnimal;
import com.dev.abrigo.exception.Exception;
import com.dev.abrigo.repository.AnimalRepository;
import com.dev.abrigo.repository.ShelterRepository;
import com.dev.abrigo.repository.VaccineRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class AnimalService {

    private ShelterRepository shelterRepository;

    private AnimalRepository animalRepository;

    private VaccineRepository vaccineRepository;

    public AnimalService(ShelterRepository shelterRepository, AnimalRepository animalRepository, VaccineRepository vaccineRepository) { //injeção via construtor
        this.shelterRepository = shelterRepository;
        this.animalRepository = animalRepository;
        this.vaccineRepository = vaccineRepository;
    }

    //CRIAR

    public Animal insert(CreateAnimal animalDTO){

        Animal newAnimal = new Animal();

        newAnimal.setAge(animalDTO.age());
        newAnimal.setSpecies(animalDTO.type());
        newAnimal.setStatus(animalDTO.status());


        // Buscando o Shelter do banco de dados usando o ID do DTO
        Optional<Shelter> optionalShelter = shelterRepository.findById(animalDTO.shelterId());

        Shelter shelter;   //Declara a variável "shelter" para armazenar o valor que será obtido do Optional

        if (optionalShelter.isPresent()) {          //verifica se o Shelter foi encontrado

            shelter = optionalShelter.get();        //Se o Optional contiver um valor, você obtém esse valor e o atribui à variável shelter

        }else{

            throw new RuntimeException("Shelter not found");
        }

        newAnimal.setShelter(shelter);

        return animalRepository.save(newAnimal);

    }

    //LISTAR POR PAGINAÇÃO

       public List<Animal> listarTodos(){

        return animalRepository.findAll();
    }

    //LISTAR PELO ID


    public Optional<Animal> findById(Long id) {

        return animalRepository.findById(id);

    }


    //ATUALIZAR

    public Animal update(Long id, UpdateAnimal updateAnimal){

      Optional<Animal> optionalAnimal = animalRepository.findById(id);

      if (optionalAnimal.isPresent()){

          Animal animal = optionalAnimal.get();  //O método get() é utilizado para obter o valor armazenado dentro do Optional(se esta vazio ou não)

          animal.setAge(updateAnimal.age());
          animal.setStatus(updateAnimal.status());

          return animalRepository.save(animal);

      }

      else throw new Exception("Animal not found");

    }

    //DELETAR

    public void delete(Long id){

        Optional<Animal> optionalAnimal = animalRepository.findById(id);

        if (optionalAnimal.isEmpty()){

            throw new Exception("Animal not found");
        }

        else shelterRepository.deleteById(id);

    }

    //ATUALIZAR VACINA


    public Animal updateVaccine(Long id, Long idVaccine){

        Optional<Animal> optionalAnimal = animalRepository.findById(id);

        Optional<Vaccine> optionalVaccine = vaccineRepository.findById(idVaccine);

        if (optionalAnimal.isPresent() && optionalVaccine.isPresent()){

            Animal animal = optionalAnimal.get();  //O método get() é utilizado para obter o valor armazenado dentro do Optional(se esta vazio ou não)

            Vaccine vaccine = optionalVaccine.get();

            VaccineApplied vaccineApplied = new VaccineApplied(vaccine, LocalDateTime.now());

            animal.getVaccineAppliedList().add(vaccineApplied);


            return animalRepository.save(animal);

        }

        else throw new Exception("Animal or Vaccine not found");

    }

}
