package com.example.sirTalion.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.sirTalion.entities.enumerate.Color;
import com.example.sirTalion.entities.enumerate.Material;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Laptop 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Name can't be blank")
    private String name;

    @Size(max = 20, message = "Laptop size should be less than 20 characters")
    @Pattern(regexp = "[\\d]+[.]?[\\d]+?[x][\\d]+[.]?[\\d]+?[x][\\d]+[.]?[\\d]+?", message = "Laptop size format should be like this: 123x123x123")
    private String size;

    @Min(value = 0, message = "Laptop min weight is 0") @NotNull(message = "Laptop weight can't be blank")
    private float weight;

    @Min(value = 0, message = "Laptop min ram slots is 0")
    private byte ramSlot;

    @Min(value = 0, message = "Laptop min drive slots is 0")
    private byte driveSlot;

    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private Color color;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Material material;

    @Pattern(regexp = "(?:[\\d][ ].+[\\n]?)+", message = "Laptop port info should be like this: 1 HDMI [new line] 2 USB Type C [new line]...")
    @Size(max = 100, message = "Laptop port info should be less than 100 characters")
    private String portInfo;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}
    , fetch = FetchType.EAGER)
    @JoinTable(name = "laptop_cpu"
    , joinColumns = @JoinColumn(name = "laptop")
    , inverseJoinColumns = @JoinColumn(name = "cpu")
    )
    private List<Cpu> cpus;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "display")
    private Display display;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "laptop_ram"
    , joinColumns = @JoinColumn(name = "laptop")
    , inverseJoinColumns = @JoinColumn(name = "ram")
    )
    private List<Ram> rams;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "laptop_drive"
    , joinColumns = @JoinColumn(name = "laptop")
    , inverseJoinColumns = @JoinColumn(name = "drive")
    )
    private List<Drive> drives;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "laptop_graphicCard"
    , joinColumns = @JoinColumn(name = "laptop")
    , inverseJoinColumns = @JoinColumn(name = "graphicCard")
    )
    private List<GraphicCard> graphicCards;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "operating_system")
    private OperatingSystem operatingSystem;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "battery")
    private Battery battery;

    // @OneToOne(mappedBy = "laptop")
    // private Product product;
    
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "manufacturer")
    private Manufacturer manufacturer;
}

