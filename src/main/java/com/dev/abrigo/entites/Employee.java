package com.dev.abrigo.entites;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String position;  //cargo

    private LocalDate admissionDate; //data da admissão

    private LocalDate resignationDate; //data da demissão


    @ManyToOne(fetch = FetchType.EAGER)  //Um Funcionario pertence a um único Abrigo.
    @JoinColumn(name = "shelter_id")   // Nome da coluna que será usada para a chave estrangeira na tabela BD
    @JsonBackReference
    private Shelter shelter;



    public Employee(){

    }



    public Employee(Long id, String name, String position, LocalDate admission, LocalDate resignation,Shelter shelter) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.admissionDate = admission;
        this.resignationDate = resignation;
        this.shelter = shelter;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDate getAdmission() {
        return admissionDate;
    }

    public void setAdmission(LocalDate admission) {
        this.admissionDate = admission;
    }

    public LocalDate getResignation() {
        return resignationDate;
    }

    public void setResignation(LocalDate resignation) {
        this.resignationDate
                = resignation;
    }

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }
}

