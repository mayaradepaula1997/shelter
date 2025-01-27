package com.dev.abrigo.repository;

import com.dev.abrigo.entites.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentRepository  extends JpaRepository<Treatment, Long> {
}
