package com.example.sirTalion.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
// import org.springframework.context.annotation.Lazy;
// import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.example.sirTalion.entities.Role;
import com.example.sirTalion.entities.User;
import com.example.sirTalion.repository.RoleRepository;
import com.example.sirTalion.service.UserService;

@Component
public class DummyData implements ApplicationListener<ApplicationReadyEvent>
{
    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        
        if (roleRepository.findByRoleName("ROLE_ADMIN") == null)
        {
            roleRepository.save(new Role("ROLE_ADMIN"));
        }

        if (roleRepository.findByRoleName("ROLE_MEMBER") == null)
        {
            roleRepository.save(new Role("ROLE_MEMBER"));
        }

        if (roleRepository.findByRoleName("ROLE_SHIPPER") == null)
        {
            roleRepository.save(new Role("ROLE_SHIPPER"));
        }

        if (userService.findByEmail("admin@gmail.com") == null)
        {
            User admin = new User();
            admin.setEmail("admin@gmail.com");
            admin.setPassword("123456");
            admin.setFullName("Hung Vip Pro");
            admin.setPhoneNumber("123456789");
            List<Role> roles = new ArrayList<>();
            roles.add(roleRepository.findByRoleName("ROLE_ADMIN"));
            admin.setRoles(roles);
            userService.saveUser(admin);
        }

        if (userService.findByEmail("member@gmail.com") == null)
        {
            User member = new User();
            member.setEmail("member@gmail.com");
            member.setPassword("123456");
            member.setFullName("Sir Talion Member");
            member.setPhoneNumber("123456789");
            List<Role> roles = new ArrayList<>();
            member.setRoles(roles);
            userService.saveUser(member);
        }

        if (userService.findByEmail("shipper@gmail.com") == null)
        {
            User shipper = new User();
            shipper.setEmail("shipper@gmail.com");
            shipper.setPassword("123456");
            shipper.setFullName("Sir Talion Shipper");
            shipper.setPhoneNumber("123456789");
            List<Role> roles = new ArrayList<>();
            roles.add(roleRepository.findByRoleName("ROLE_SHIPPER"));
            shipper.setRoles(roles);
            userService.saveUser(shipper);
        }
    }
    
}
