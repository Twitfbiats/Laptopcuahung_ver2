package com.example.sirTalion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sirTalion.entities.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>
{
    
}
