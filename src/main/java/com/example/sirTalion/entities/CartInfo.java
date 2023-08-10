package com.example.sirTalion.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.sirTalion.repository.CartRepository;
import com.example.sirTalion.repository.ProductRepository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CartInfo 
{
    @EmbeddedId
    private CartInfoKey id;

    private int quantity;

    public CartInfo(long cart, long product, int quantity)
    {
        this.id = new CartInfoKey(cart, product); this.cart = new Cart(cart); this.product = new Product(product);
        this.quantity = quantity;
    }

    public CartInfo(long cart, long product)
    {
        this.id = new CartInfoKey(cart, product); this.cart = new Cart(cart); this.product = new Product(product);
    }

    @ManyToOne
    @MapsId(value = "cartId")
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @MapsId(value = "productId")
    @JoinColumn(name = "product_id")
    private Product product;
}
