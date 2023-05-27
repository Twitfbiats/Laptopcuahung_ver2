package com.example.sirTalion.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderInfo 
{
    @EmbeddedId 
    private OrderInfoKey orderInfoKey;

    @ManyToOne
    @MapsId(value = "order")
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @MapsId(value = "product")
    @JoinColumn(name = "product_id")
    private Product product;

    private long total;
    private int quantity;
    private int receiveQuantity;
}
