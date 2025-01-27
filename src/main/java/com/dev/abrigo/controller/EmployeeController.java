package com.dev.abrigo.controller;

import com.dev.abrigo.entites.Employee;
import com.dev.abrigo.entites.Shelter;
import com.dev.abrigo.entites.dto.employee.CreateEmployee;
import com.dev.abrigo.entites.dto.employee.UpdateEmployee;
import com.dev.abrigo.entites.dto.employee.UpdateResignation;
import com.dev.abrigo.service.EmployeeService;
import com.dev.abrigo.service.ShelterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {


    private EmployeeService employeeService;

    private ShelterService shelterService;

    public EmployeeController(EmployeeService employeeService, ShelterService shelterService) {
        this.employeeService = employeeService;
        this.shelterService = shelterService;
    }


    //CRIAR
    @PostMapping
    public ResponseEntity<?> insert(@RequestBody CreateEmployee createEmployee){


        //Verifica se o abrigo existe
        Optional<Shelter> optionalShelter = shelterService.findById(createEmployee.shelterId());

        if(optionalShelter.isEmpty()){

            //Se não existir lança um erro
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shelter not found");
        }

        //Se existir, instancia a classe de Funcionario e classe a classe de serviço
        Employee employee = employeeService.insert(createEmployee);

        //Retorna uma resposta com status 200 OK
        return ResponseEntity.ok(employee);
    }


    //LISTAR TODOS

    @GetMapping
    public ResponseEntity <List<Employee>> findAll(){

        List<Employee> employeeList = employeeService.findAll();

        return ResponseEntity.ok().body(employeeList);

    }

    //LISTAR ATRAVEZ DO ID

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){ //<?> indica que o corpo da resposta pode ser de qualquer tipo

        if (id == null ||id <= 0){

            return ResponseEntity.badRequest().build(); //Status 400 e sem corpo, o que indica que a solicitação é inválida.

        }
        Optional<Employee> optionalEmployee = employeeService.findById(id);

        if (optionalEmployee.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee is not found");
        }

            return ResponseEntity.ok(optionalEmployee);
        }



    //ATUALIZAR
    @PutMapping(value = "/{id}")

    public ResponseEntity<?> update(@PathVariable Long id , @RequestBody UpdateEmployee updateEmployee){

       if (id == null ||id <= 0){

           return ResponseEntity.badRequest().build();

       }
       Optional<Employee> optionalEmployee = employeeService.findById(id);

       if(optionalEmployee.isEmpty()){

           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee is not found");

       }

           Employee employee = employeeService.update(id,updateEmployee);

           employee.setName(updateEmployee.name());
           employee.setPosition(updateEmployee.position());

           return ResponseEntity.ok().body(employee);

       }

       //DELETAR

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        if (id == null ||id <= 0 ) {

            return ResponseEntity.badRequest().build();
        }

        Optional<Employee> optionalEmployee = employeeService.findById(id);

        if (optionalEmployee.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee is not found");
        }

        //Chamar o serviço para deletar o recurso
        employeeService.delete(id);

        return ResponseEntity.noContent().build();
    }


    //FUNÇÃO PARA INSERIR DATA DE DEMISSÃO

    @PutMapping(value = "/resignation/{id}")
    public ResponseEntity<?> updateDismissalDate (@PathVariable Long id, @RequestBody UpdateResignation updateResignation){

     if (id == null || id <= 0){

         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is required");
     }

     Optional<Employee> optionalEmployee = employeeService.findById(id);

     if (optionalEmployee.isPresent()){

         Employee employee = employeeService.updateDismissalDate(id, updateResignation );

         return ResponseEntity.ok().body(employee);

     }

     return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee is not found");

    }


    }






