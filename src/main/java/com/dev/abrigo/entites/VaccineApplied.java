package com.dev.abrigo.entites;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.util.Optional;

@Embeddable
public class VaccineApplied { //Classe auxiliar ou classe apoio: Tipo mais complexo da entidade para ser utilizado na classe Animal (como atributo)


    @ManyToOne
    private Vaccine vaccine;

    private LocalDateTime applicationDate;


    public VaccineApplied (){


    }


    public VaccineApplied(Vaccine vaccine, LocalDateTime applicationDate) {
        this.vaccine = vaccine;
        this.applicationDate = applicationDate;
    }

    public VaccineApplied(Optional<Vaccine> optionalVaccine, LocalDateTime now) {
    }


    public Vaccine getVaccine() {
        return vaccine;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }

    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDateTime applicationDate) {
        this.applicationDate = applicationDate;
    }
}
