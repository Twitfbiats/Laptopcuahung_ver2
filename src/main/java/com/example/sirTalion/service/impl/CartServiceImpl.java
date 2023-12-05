package com.example.sirTalion.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sirTalion.entities.Cart;
import com.example.sirTalion.repository.CartRepository;
import com.example.sirTalion.service.CartService;

@Service
public class CartServiceImpl implements CartService
{
    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart save(Cart cart) 
    {
        return cartRepository.save(cart);
    }
    
}
