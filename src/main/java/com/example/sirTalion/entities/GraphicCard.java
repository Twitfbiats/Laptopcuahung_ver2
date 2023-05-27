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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.sirTalion.entities.enumerate.GraphicCardType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class GraphicCard 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Graphic Card model name can't be blank")
    @Size(max = 50, message = "Graphic card model should be less than 50 characters")
    private String model;

    @Min(value = 0, message = "Graphic Card min memory is 0")
    private short memory;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Graphic Card type can't be blank")
    @Column(length = 15)
    private GraphicCardType graphicCardType;

    @ManyToOne
    @JoinColumn(name = "manufacturer")
    private Manufacturer manufacturer;
}
