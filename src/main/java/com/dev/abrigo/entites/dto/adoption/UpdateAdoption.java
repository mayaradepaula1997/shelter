package com.dev.abrigo.entites.dto.adoption;

import java.time.LocalDate;

public record UpdateAdoption(LocalDate adoptionDate, String adoptedName, String cpfAdopte){
}
