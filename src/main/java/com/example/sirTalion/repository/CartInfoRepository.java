package com.example.sirTalion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sirTalion.entities.CartInfo;
import com.example.sirTalion.entities.CartInfoKey;

public interface CartInfoRepository extends JpaRepository<CartInfo, CartInfoKey>
{
    List<CartInfo> findAllByCartId(long id);
}
