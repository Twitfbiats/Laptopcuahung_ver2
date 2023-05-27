package com.example.sirTalion.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Battery 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(max = 50, message = "Battery model should be less than 50 characters")
    private String model;

    @Min(value = 0, message = "Battery min voltage is 0")
    @NotNull(message = "Battery voltage can't be null")
    private float voltages;

    @Min(value = 0, message = "Battery estimated working time should be greater than zero")
    private byte estimatedWorkingTime;
}
