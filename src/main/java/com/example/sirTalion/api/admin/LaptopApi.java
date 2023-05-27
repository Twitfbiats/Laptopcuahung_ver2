package com.example.sirTalion.api.admin;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sirTalion.entities.enumerate.Color;

@RestController
@RequestMapping("/api/laptop")
public class LaptopApi 
{
    @CrossOrigin(origins = "*")
    @GetMapping("/color")
    public Color[] getAllLaptopColor()
    {
        return Color.values();
    }
}
