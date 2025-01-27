package com.dev.abrigo.entites.dto.animal;

import com.dev.abrigo.entites.enums.DonationStatus;

public record UpdateAnimal(String age, DonationStatus status) {
}
