package com.dev.abrigo.entites;

import com.dev.abrigo.entites.enums.DonationStatus;
import com.dev.abrigo.entites.enums.SpeciesType;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String age; //idade

    private SpeciesType species;  //enum - espécie

    private DonationStatus status; //enum - status do tratamento



    public Animal(){

    }


    public Animal(Long id, String age, SpeciesType species, DonationStatus status) {
        this.id = id;
        this.age = age;
        this.species = species;
        this.status = status;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public SpeciesType getSpecies() {
        return species;
    }

    public void setSpecies(SpeciesType species) {
        this.species = species;
    }

    public DonationStatus getStatus() {
        return status;
    }

    public void setStatus(DonationStatus status) {
        this.status = status;
    }
}
