package com.example.sirTalion.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Bill 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long total;

    @Size(max = 20, message = "Bill status should be less than 20 characters")
    private String status;

    @DateTimeFormat(pattern = "yyyy/MM/dd hh:mm")
    private Date payDate;

    private String payWith;

    @Column(length = 1000)
    private String note;

    @ManyToOne
    @JoinColumn(name = "purchaser_id")
    private User purchaser;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
