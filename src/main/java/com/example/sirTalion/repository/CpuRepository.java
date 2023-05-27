package com.example.sirTalion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sirTalion.entities.Cpu;

@Repository
public interface CpuRepository extends JpaRepository<Cpu, Long>
{
    
}
