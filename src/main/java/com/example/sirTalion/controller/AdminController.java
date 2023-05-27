package com.example.sirTalion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.sirTalion.entities.User;
import com.example.sirTalion.repository.CategoryRepository;
import com.example.sirTalion.repository.ManufacturerRepository;
import com.example.sirTalion.service.UserService;

@Controller
@RequestMapping("/admin")
@SessionAttributes("loggedInUser")
public class AdminController 
{
    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserService userService;

    @ModelAttribute("loggedInUser")
    public User loggedInUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByEmail(authentication.getName());
    }

    @GetMapping
    public String adminPage(Model model)
    {
        return "admin/admin-site";
    }

    @GetMapping("/manufacturer")
    public String manufacturerManagePage()
    {
        return "admin/manufacturer-manage";
    }

    @GetMapping("/category")
    public String categoryManagePage()
    {
        return "admin/category-manage";
    }

    @GetMapping("/product")
    public String ProductManagePage(Model model)
    {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("manufacturers", manufacturerRepository.findAll());

        return "admin/product-manage";
    }
}
