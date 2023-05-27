package com.example.sirTalion.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.sirTalion.dto.AccountDTO;
import com.example.sirTalion.entities.Provider;
import com.example.sirTalion.entities.Role;
import com.example.sirTalion.entities.User;
import com.example.sirTalion.repository.RoleRepository;
import com.example.sirTalion.repository.UserRepository;
import com.example.sirTalion.service.UserService;

@Transactional
@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired public BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findByEmail(String email) 
    {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByConfirmationToken(String confirmationToken) 
    {
        return null;
    }

    @Override
    public User saveUser(User user) 
    {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if (user.getRoles() == null)
        {
            List<Role> roles = new ArrayList<>();
            roles.add(roleRepository.findByRoleName("ROLE_MEMBER"));
            user.setRoles(roles);
        } else user.getRoles().add(roleRepository.findByRoleName("ROLE_MEMBER"));
        
        return userRepository.save(user);
    }

    @Override
    public User findById(long id) 
    {
        return userRepository.findById(id).get();
    }

    @Override
    public User updateUser(User user) 
    {
        return userRepository.save(user);
    }

    @Override
    public void changePass(User user, String newPassword) 
    {
        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public Page<User> getUserByRoles(List<Role> role, int page) 
    {
        return userRepository.findAllByRolesIn(role, PageRequest.of(page-1, 6));
    }

    @Override
    public List<User> getUserByRoles(List<Role> role) 
    {
        return userRepository.findAllByRolesIn(role);
    }

    @Override
    public User saveUserForAdmin(AccountDTO dto) 
    {
        User user = new User();
        user.setFullName(dto.getFullName());
        user.setAddress(dto.getAddress());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));

        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByRoleName(dto.getRole()));
        user.setRoles(roles);

        return userRepository.save(user);
    }

    @Override
    public void deleteById(long id) 
    {
        userRepository.deleteById(id);
    }
    
    @Override
    public void processOauth2PostLogin(String email, String name) 
    {
        User user = userRepository.findByEmail(email);
        
        if (user == null)
        {
            User newUser = new User();
            newUser.setFullName(name);
            newUser.setEmail(email);
            newUser.setProvider(Provider.GOOGLE);
            userRepository.save(newUser);
        }
    }
}
