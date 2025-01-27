package com.dev.abrigo.entites.dto.treatment;

import com.dev.abrigo.entites.enums.TreatmentStatus;

import java.time.LocalDate;

public record UpdateTreatment(String name, LocalDate startDate, TreatmentStatus treatmentStatus) {
}
