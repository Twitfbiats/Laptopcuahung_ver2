package com.example.sirTalion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sirTalion.entities.Role;
import com.example.sirTalion.repository.RoleRepository;
import com.example.sirTalion.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService
{
    
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByRole(String roleName) 
    {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public List<Role> findAllRole() 
    {
        return roleRepository.findAll();
    }

    
}
