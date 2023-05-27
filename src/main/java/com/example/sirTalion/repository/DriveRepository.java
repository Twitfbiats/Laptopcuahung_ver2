package com.example.sirTalion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sirTalion.entities.Drive;

@Repository
public interface DriveRepository extends JpaRepository<Drive, Long>
{
    
}
