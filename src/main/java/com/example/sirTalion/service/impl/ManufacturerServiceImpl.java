package com.example.sirTalion.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.sirTalion.entities.Manufacturer;
import com.example.sirTalion.repository.ManufacturerRepository;
import com.example.sirTalion.service.ManufacturerService;

@Service
public class ManufacturerServiceImpl implements ManufacturerService
{
    @Autowired
    private ManufacturerRepository manufacturerRepository;

    public Page<Manufacturer> findAllBy(int page, int limit) 
    {
        return manufacturerRepository.findAllBy(PageRequest.of(page-1, limit));
    }

    public Manufacturer findById(long id) 
    {
        return manufacturerRepository.findById(id).get();
    }

    public void saveOrUpdate(Manufacturer manufacturer) 
    {
        manufacturerRepository.save(manufacturer);
    }

    public void deleteById(long id) 
    {
        manufacturerRepository.deleteById(id);
    }
}
