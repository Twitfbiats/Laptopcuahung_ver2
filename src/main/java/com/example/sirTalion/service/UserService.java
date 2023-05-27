package com.example.sirTalion.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.sirTalion.dto.AccountDTO;
import com.example.sirTalion.entities.Role;
import com.example.sirTalion.entities.User;

public interface UserService 
{
    
    User findByEmail(String email);

    User findByConfirmationToken(String confirmationToken);

    User saveUser(User user);

    User findById(long id);

    User updateUser(User user);

    void changePass(User user, String newPassword);

    Page<User> getUserByRoles(List<Role> role, int page);

    List<User> getUserByRoles(List<Role> role);

    User saveUserForAdmin(AccountDTO dto);

    void deleteById(long id);

    void processOauth2PostLogin(String email, String name);
}
