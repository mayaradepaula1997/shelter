package com.dev.abrigo.entites.dto.employee;

import java.time.LocalDate;

public record CreateEmployee(String name, String position, LocalDate admissionDate,
                             LocalDate resignationDate, Long shelterId){

}