package com.example.sirTalion.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartInfoKey implements Serializable
{
    @Column(name = "cart_id")
    private long cartId;

    @Column(name = "product_id")
    private long productId;

    @Override
    public int hashCode() 
    {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object arg0) 
    {
        return super.equals(arg0);
    }
}
