package com.example.sirTalion.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Manufacturer 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;    

    @NotEmpty(message = "name can't be empty")
    @Size(max = 100, message = "Manufacturer name should be less than 100 characters")
    private String name;

    // @OneToMany(mappedBy = "manufacturer")
    // private List<Laptop> laptops;

    // @OneToMany(mappedBy = "manufacturer")
    // private List<Cpu> cpus;
}
