package com.example.sirTalion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sirTalion.entities.GraphicCard;

@Repository
public interface GraphicCardRepository extends JpaRepository<GraphicCard, Long>
{
    
}
