package com.example.sirTalion.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OperatingSystem 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Operating System name can't be blank")
    @Size(max = 50, message = "Operating System name should be less than 50 characters")
    private String name;

    @Min(value = 1, message = "Operating System type should be a number > 0 like 64 or 32")
    private byte type;
}
