package com.dev.abrigo.repository;

import com.dev.abrigo.entites.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
}
