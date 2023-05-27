package com.example.sirTalion.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sirTalion.entities.Role;
import com.example.sirTalion.entities.User;

public interface UserRepository extends JpaRepository<User, Long>
{
    User findByEmail(String email);

    Page<User> findAllByRolesIn(List<Role> roles, Pageable of);

    List<User> findAllByRolesIn(List<Role> role);
}
