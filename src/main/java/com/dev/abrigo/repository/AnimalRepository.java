package com.dev.abrigo.repository;

import com.dev.abrigo.entites.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository  extends JpaRepository<Animal, Long> {
}
