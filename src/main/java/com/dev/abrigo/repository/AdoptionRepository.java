package com.dev.abrigo.repository;


import com.dev.abrigo.entites.Adoption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptionRepository extends JpaRepository <Adoption, Long> {
}
