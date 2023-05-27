package com.example.sirTalion.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.sirTalion.entities.Manufacturer;

public interface ManufacturerService 
{
    Page<Manufacturer> findAllBy(int page, int limit);
    Manufacturer findById(long id);
    void saveOrUpdate(Manufacturer manufacturer);
    void deleteById(long id);
    List<Manufacturer> findAll();
}
