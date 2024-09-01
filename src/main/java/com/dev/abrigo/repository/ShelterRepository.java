package com.dev.abrigo.repository;

import com.dev.abrigo.entites.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelterRepository  extends JpaRepository<Shelter, Long> {
}
