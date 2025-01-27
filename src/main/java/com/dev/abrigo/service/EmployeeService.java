package com.dev.abrigo.service;


import com.dev.abrigo.entites.Employee;
import com.dev.abrigo.entites.Shelter;
import com.dev.abrigo.entites.dto.employee.CreateEmployee;
import com.dev.abrigo.entites.dto.employee.UpdateEmployee;
import com.dev.abrigo.entites.dto.employee.UpdateResignation;
import com.dev.abrigo.repository.EmployeeRepository;
import com.dev.abrigo.repository.ShelterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    private ShelterRepository shelterRepository;


    public EmployeeService(EmployeeRepository employeeRepository, ShelterRepository shelterRepository) {
        this.employeeRepository = employeeRepository;
        this.shelterRepository = shelterRepository;
    }

    //CRIAR
    public Employee insert(CreateEmployee createEmployee){

        Employee employee = new Employee();

        employee.setName(createEmployee.name());
        employee.setPosition(createEmployee.position());
        employee.setAdmission(createEmployee.admissionDate());
        employee.setResignation(createEmployee.resignationDate());

        Optional<Shelter> optionalShelter = shelterRepository.findById(createEmployee.shelterId()); //Verificando se o abrigo existe para poder instanciar

        Shelter shelter; //Declarada uma variável do tipo Abrigo,
                        // que mais tarde irá armazenar o abrigo encontrado

        if(optionalShelter.isPresent()) {

            shelter = optionalShelter.get(); //Se o Optional tiver o valor, você obtém esse valor e o atribui à variável animal

            employee.setShelter(shelter);
        }

        if(!optionalShelter.isPresent()){

            throw new RuntimeException("Animal not found");

        }


        return employeeRepository.save(employee);


    }


    //LISTAR TODOS

    public List<Employee> findAll(){

        return employeeRepository.findAll();
    }


    //LISTAR PELO ID

    public Optional<Employee> findById(Long id){  //verificar se o abrigo existe

        return employeeRepository.findById(id);

    }

    //ATUALIAZAR

    public Employee update(Long id, UpdateEmployee updateEmployee) {

        Optional<Employee> optionalEmployee = employeeRepository.findById(id);


        if(optionalEmployee.isPresent()) {

            Employee employee = optionalEmployee.get(); // Inicializa a variável apenas se estiver presente

            // Atualiza as informações do funcionário
            employee.setName(updateEmployee.name());
            employee.setPosition(updateEmployee.position());

            // Salva e retorna o funcionário atualizado
            return employeeRepository.save(employee);

        }

            throw new RuntimeException("Employee not found");
        }

        //DELETAR

    public void delete (Long id){

        employeeRepository.deleteById(id);


    }


    //FUNÇÃO PARA INSERIR DATA DE DEMISSÃO

    public Employee updateDismissalDate (Long id, UpdateResignation updateResignation){

        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {

            Employee employee = optionalEmployee.get();

            employee.setResignation(updateResignation.resignationDate());

            return employeeRepository.save(employee);
        }

        throw  new RuntimeException("Employee not found");
    }


    }





