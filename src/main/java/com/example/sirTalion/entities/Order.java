package com.example.sirTalion.entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "Orders")
@Getter
@Setter
public class Order 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String address;
    private String note;
    @Size(max = 50, message = "Order receiver name should be less than 50 characters")
    private String receiverName;
    private String receiverEmail;

    @DateTimeFormat(pattern = "yyyy/MM/dd hh:mm")
    private Date orderDate;
    @DateTimeFormat(pattern = "yyyy/MM/dd hh:mm")
    private Date shipDate;
    @DateTimeFormat(pattern = "yyyy/MM/dd hh:mm")
    private Date receiveDate;

    @Size(max = 15, message = "Order receiver phone number should be less than 15 characters")
    private String receivePhoneNumber;

    private double cost;
    private double total;

    @Size(max = 20, message = "Order status should be less than 20 characters")
    private String status;

    // @ManyToOne
    // @JoinColumn(name = "purchaser_id")
    // private User purchaser;

    @ManyToOne
    @JoinColumn(name = "shipper_id")
    private User shipper;

    // @OneToMany(mappedBy = "order")
    // private List<OrderInfo> orderInfos;
}
