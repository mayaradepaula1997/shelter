package com.dev.abrigo.repository;

import com.dev.abrigo.entites.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository  extends JpaRepository<Employee, Long> {
}
