package com.example.sirTalion.service;

import java.util.List;

import com.example.sirTalion.entities.Role;

public interface RoleService 
{
    
    Role findByRole(String roleName);
    List<Role> findAllRole();
    
}
