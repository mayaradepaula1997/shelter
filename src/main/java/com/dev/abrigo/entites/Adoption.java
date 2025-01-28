package com.dev.abrigo.entites;


import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

//ADOÇÃO
@Entity
@Table(name = "tb_adoption")
public class Adoption {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate adoptionDate;  //Data da Adoção

    private String adoptedName;  //Nome do Adotante

    private String cpfAdopte;


    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = true)
    @OnDelete(action = OnDeleteAction.SET_NULL)
   // @JoinColumn(name = "animal_id")
    private Animal animal;  // Relacionamento com o Animal adotado


    public Adoption(){     //Construtor vazio

    }

    public Adoption(Long id, LocalDate adoptionDate, String adoptedName, String cpfAdopte, Animal animal) {
        this.id = id;
        this.adoptionDate = adoptionDate;
        this.adoptedName = adoptedName;
        this.cpfAdopte = cpfAdopte;
        this.animal = animal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getAdoptionDate() {
        return adoptionDate;
    }

    public void setAdoptionDate(LocalDate adoptionDate) {
        this.adoptionDate = adoptionDate;
    }

    public String getAdoptedName() {
        return adoptedName;
    }

    public void setAdoptedName(String adoptedName) {
        this.adoptedName = adoptedName;
    }

    public String getCpfAdopte() {
        return cpfAdopte;
    }

    public void setCpfAdopte(String cpfAdopte) {
        this.cpfAdopte = cpfAdopte;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}
