package com.example.sirTalion.api.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sirTalion.entities.Battery;
import com.example.sirTalion.entities.Display;
import com.example.sirTalion.entities.OperatingSystem;
import com.example.sirTalion.entities.enumerate.Color;
import com.example.sirTalion.entities.enumerate.DisplayType;
import com.example.sirTalion.entities.enumerate.Material;
import com.example.sirTalion.entities.enumerate.PanelType;
import com.example.sirTalion.entities.enumerate.RamType;
import com.example.sirTalion.repository.BatteryRepository;
import com.example.sirTalion.repository.DisplayRepository;
import com.example.sirTalion.repository.OperatingSystemRepository;

@RestController
@RequestMapping("/api/laptop")
public class LaptopApi 
{
    @Autowired
    private DisplayRepository displayRepository;

    @Autowired
    private OperatingSystemRepository operatingSystemRepository;

    @Autowired
    private BatteryRepository batteryRepository;

    @CrossOrigin(origins = "*")
    @GetMapping("/color")
    public Color[] getAllLaptopColor()
    {
        return Color.values();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/material")
    public Material[] getAllLaptopMaterial()
    {
        return Material.values();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/display/panel-type")
    public PanelType[] getAllLaptopDisplayPanelTypes()
    {
        return PanelType.values();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/display/display-type")
    public DisplayType[] getAllLaptopDisplayTypes()
    {
        return DisplayType.values();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/display/all")
    public List<Display> getAllLaptopDisplay()
    {
        return displayRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/operating-system/all")
    public List<OperatingSystem> getAllOperatingSystems()
    {
        return operatingSystemRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/battery/all")
    public List<Battery> getAllBatteries()
    {
        return batteryRepository.findAll();
    }
}
