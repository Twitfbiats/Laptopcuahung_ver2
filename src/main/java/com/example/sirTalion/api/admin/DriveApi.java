package com.example.sirTalion.api.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sirTalion.entities.Drive;
import com.example.sirTalion.entities.enumerate.DriveType;
import com.example.sirTalion.repository.DriveRepository;

@RestController
@RequestMapping("/api/drive")
public class DriveApi
{
    @Autowired
    private DriveRepository driveRepository;

    @CrossOrigin(origins = "*")
    @GetMapping("/drive-type")
    public DriveType[] getAllDriveTypes()
    {
        return DriveType.values();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/all")
    public List<Drive> getAllDrives()
    {
        return driveRepository.findAll();
    }
}

