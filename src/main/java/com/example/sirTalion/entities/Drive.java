package com.example.sirTalion.entities;

import javax.persistence.CascadeType;
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

import com.example.sirTalion.entities.enumerate.DriveType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Drive 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(max = 50, message = "Drive model should be less than 50 characters")
    private String model;

    @Min(value = 0, message = "Drive min size is 0") @NotNull(message = "Drive size can't be blank")
    private short size;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private DriveType driveType;

    @Min(value = 0, message = "Drive min write is 0")
    private short maxWrite;
    @Min(value = 0, message = "Drive min read is 0")
    private short maxRead;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "manufacturer")
    private Manufacturer manufacturer;
}
