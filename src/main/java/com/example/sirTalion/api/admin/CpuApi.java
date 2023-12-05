package com.example.sirTalion.api.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sirTalion.entities.Cpu;
import com.example.sirTalion.repository.CpuRepository;

@RestController
@RequestMapping("/api/cpu")
public class CpuApi 
{
    @Autowired
    private CpuRepository cpuRepository;

    @GetMapping("/all")
    public List<Cpu> getAllCpus()
    {
        return cpuRepository.findAll();
    }
}
