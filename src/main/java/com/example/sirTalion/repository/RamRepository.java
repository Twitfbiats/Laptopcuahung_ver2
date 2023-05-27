package com.example.sirTalion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sirTalion.entities.Ram;

@Repository
public interface RamRepository extends JpaRepository<Ram, Long>
{
    
}
