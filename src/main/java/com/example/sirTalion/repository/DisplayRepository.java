package com.example.sirTalion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sirTalion.entities.Display;

@Repository
public interface DisplayRepository extends JpaRepository<Display, Long>
{
    
}
