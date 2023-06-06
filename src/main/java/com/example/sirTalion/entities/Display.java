package com.example.sirTalion.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.sirTalion.entities.enumerate.DisplayType;
import com.example.sirTalion.entities.enumerate.PanelType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Display 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Min(value = 0, message = "Display min size is 0") @NotNull(message = "Display size can't be blank")
    private float size;

    private String technology;

    @Size(max = 11, message = "Display max resolution should be less than 11 characters")
    @Pattern(regexp = "[\\d]+[x][\\d]+", message = "Display resolution format should be NumberxNumber")
    @NotBlank(message = "Display resolution can't be blank")
    private String resolution;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private DisplayType displayType;

    @Min(value = 0, message = "Display min refresh rate is 0") @NotNull(message = "Display refresh rate can't be blank")
    private short refreshRate;

    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private PanelType panelType;
}

