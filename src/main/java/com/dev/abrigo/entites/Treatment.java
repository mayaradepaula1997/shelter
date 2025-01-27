package com.dev.abrigo.entites;


import com.dev.abrigo.entites.enums.TreatmentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "tb_treatment")
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate startDate; //inicio do tratamento

    private LocalDate endDate;  //final do tratemento

    private TreatmentStatus treatmentStatus; //enum - status do tratamento

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;


    public Treatment(){

    }

    public Treatment(Long id, String name, LocalDate startDate, LocalDate endDate, TreatmentStatus treatmentStatus, Animal animal) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.treatmentStatus = treatmentStatus;
        this.animal = animal;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public TreatmentStatus getTreatmentStatus() {
        return treatmentStatus;
    }

    public void setTreatmentStatus(TreatmentStatus treatmentStatus) {
        this.treatmentStatus = treatmentStatus;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}
