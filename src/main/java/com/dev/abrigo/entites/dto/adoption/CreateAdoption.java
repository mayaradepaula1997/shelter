package com.dev.abrigo.entites.dto.adoption;

import java.time.LocalDate;

public record CreateAdoption(LocalDate adoptionDate, String adoptedName, String cpfAdopte, Long animalId) {
}
