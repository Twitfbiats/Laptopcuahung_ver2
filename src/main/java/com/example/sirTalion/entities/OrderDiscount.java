package com.example.sirTalion.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderDiscount
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne()
    @JoinColumn(name = "order_id")
    private Order order;

    @Min(value = 1, message = "Discout value should not be less than 1")
    private double discountValue;
    @Max(value = 20)
    private String discountUnit;
    @Max(value = 20)
    private String couponCode;

    @DateTimeFormat(pattern = "yyyy/MM/dd hh:mm")
    private Date dateCreated;
    @DateTimeFormat(pattern = "yyyy/MM/dd hh:mm")
    private Date validUntil;
}
