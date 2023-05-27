package com.example.sirTalion.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.sirTalion.entities.enumerate.RamType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Ram 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(max = 50, message = "Ram model should be less than 50 characters")
    private String model;

    @Min(value = 0, message = "Ram min size is 0") @NotNull(message = "Ram size can't be blank")
    private short size;

    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private RamType ramType;

    @Min(value = 0, message = "Ram min bus is 0")
    private short bus;

    @ManyToOne
    @JoinColumn(name = "manufacturer")
    private Manufacturer manufacturer;
}
