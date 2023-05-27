package com.example.sirTalion.entities;

import java.io.IOException;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Product 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "laptop")
    private Laptop laptop;

    @OneToOne()
    @JoinColumn(name = "cpu")
    private Cpu cpu;

    @OneToOne()
    @JoinColumn(name = "ram")
    private Ram ram;

    @OneToOne()
    @JoinColumn(name = "drive")
    private Drive drive;

    @OneToOne()
    @JoinColumn(name = "graphicCard")
    private GraphicCard graphicCard;

    @Transient
    @JsonIgnore
    private MultipartFile image;

    @Min(value = 1, message = "Product price should't be blank and must be greater than 1")
    @JsonSerialize(using = DoubleDeserializer.class)
    private double price;

    @Min(value = 0, message = "Product sold shouldn't be negative")
    private int sold;

    @Min(value = 0, message = "Product stock shouldn't be negative")
    private short stock;

    @Pattern(regexp = "[\\d]+[ ].+", message = "Product warranty should be like this: 1 years or 24 months or ...")
    private String warranty;

    @Column(length = 10000)
    private String embedded3DModel;

    // @OneToMany(mappedBy = "product")
    // private List<CartInfo> cartInfos;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    // @OneToMany(mappedBy = "product")
    // private List<OrderInfo> orderInfos;
}

// use this for deserializing price to plain number (10E7 -> 10000000)
class DoubleDeserializer extends JsonSerializer<Double>
{
    public void serialize(Double value, JsonGenerator gen, SerializerProvider serializers) throws IOException 
    {
        BigDecimal d = new BigDecimal(value);
        gen.writeNumber(d.toPlainString());
    }
}
