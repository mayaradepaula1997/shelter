package com.dev.abrigo.entites.dto.animal;


import com.dev.abrigo.entites.enums.DonationStatus;
import com.dev.abrigo.entites.enums.SpeciesType;

public record CreateAnimal(String age, SpeciesType type, DonationStatus status, Long shelterId) {
}
