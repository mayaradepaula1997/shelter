package com.dev.abrigo.entites;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_shelter")
public class Shelter { //Abrigo


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address; //endereço

    private String phone; //telefone

    private String email;


    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY) //Um abrigo pode ser varios animais
    @JsonIgnoreProperties("shelter")
    private List<Animal> animal;


    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY) //Um abrigo pode ser varios funcionario
    @JsonIgnoreProperties("shelter")
    private List<Employee> employees;

    public Shelter(){

    }

    public Shelter(Long id, String name, String address, String phone, String email, List<Animal> animal, List<Employee> employees) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.animal = animal;
        this.employees = employees;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Animal> getAnimal() {
        return animal;
    }

    public void setAnimal(List<Animal> animal) {
        this.animal = animal;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}


