package com.example.sirTalion.api.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sirTalion.entities.Ram;
import com.example.sirTalion.entities.enumerate.RamType;
import com.example.sirTalion.repository.RamRepository;

@RestController
@RequestMapping("/api/ram")
public class RamApi 
{
    @Autowired
    private RamRepository ramRepository;

    @CrossOrigin(origins = "*")
    @GetMapping("/ram-type")
    public RamType[] getAllRamTypes()
    {
        return RamType.values();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/all")
    public List<Ram> getAllRams()
    {
        return ramRepository.findAll();
    }
}
