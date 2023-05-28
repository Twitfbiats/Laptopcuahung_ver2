
package com.example.sirTalion.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cpu 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "CPU model can't be blank")
    @Size(max = 50, message = "Cpu model should be less than 50 characters")
    private String model;

    @NotNull(message = "Core can't be null") @Min(value = 1, message = "Min 1")
    private short core;

    @NotNull(message = "Thread can't be null") @Min(value = 1, message = "Min 1")
    private short thread;

    @NotNull(message = "Processor Speed can't be null") @Min(value = 1, message = "Min 1")
    private float processorSpeed;

    @NotNull(message = "Max Processor Speed can't be null") @Min(value = 1, message = "Min 1")
    private float maxProcessorSpeed;

    private String additionalInfo;

    // @ManyToMany(mappedBy = "cpus")
    // private List<Laptop> laptops;

    @ManyToOne
    @JoinColumn(name = "manufacturer", nullable = true)
    private Manufacturer manufacturer;

    // @OneToOne(mappedBy = "cpu")
    // private Product product;
}
