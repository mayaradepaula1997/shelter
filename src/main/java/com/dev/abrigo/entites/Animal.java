package com.dev.abrigo.entites;

import com.dev.abrigo.entites.enums.DonationStatus;
import com.dev.abrigo.entites.enums.SpeciesType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String age; //idade

    private SpeciesType species;  //enum - espécie

    private DonationStatus status; //enum - status da doação


    @ManyToOne(fetch = FetchType.EAGER)   //Um Animal pertence a um único Abrigo.
    @JoinColumn(name = "shelter_id")   // Nome da coluna que será usada para a chave estrangeira na tabela BD
    @JsonBackReference
    private Shelter shelter;

    @ElementCollection
    private List<VaccineApplied> vaccineAppliedList;


    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY) //Um animal pode ser varios tratamentos
    @JsonIgnoreProperties("animal")
    private List<Treatment> treatments;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)  //Um Animal pertence a uma única Adoção.
    @JoinColumn(name = "adoption_id")   // Nome da coluna que será usada para a chave estrangeira na tabela BD
    @JsonBackReference
    private Adoption adoption;


    public Animal(){

    }

    public Animal(Long id, String age, SpeciesType species, DonationStatus status, Shelter shelter, List<VaccineApplied> vaccineAppliedList) {
        this.id = id;
        this.age = age;
        this.species = species;
        this.status = status;
        this.shelter = shelter;
        this.vaccineAppliedList = vaccineAppliedList;
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

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;

    }

    public List<VaccineApplied> getVaccineAppliedList() {
        return vaccineAppliedList;
    }
}
