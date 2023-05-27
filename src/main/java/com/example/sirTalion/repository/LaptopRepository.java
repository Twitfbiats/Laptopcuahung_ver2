package com.example.sirTalion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sirTalion.entities.Laptop;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long>
{
    
}
