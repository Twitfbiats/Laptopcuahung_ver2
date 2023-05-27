package com.example.sirTalion.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class OrderInfoKey implements Serializable
{
    @Column(name = "order_id")
    private long order;

    @Column(name = "product_id")
    private long product;

    @Override public boolean equals(Object arg0) 
    {
        return super.equals(arg0);
    }

    @Override public int hashCode() 
    {
        return super.hashCode();
    }
}
