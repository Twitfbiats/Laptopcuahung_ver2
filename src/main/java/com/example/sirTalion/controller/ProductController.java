package com.example.sirTalion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.sirTalion.repository.ProductRepository;

@Controller
public class ProductController 
{
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("laptop/{id}")
    public String viewProduct(@PathVariable long id, Model model)
    {
        model.addAttribute("product", productRepository.findById(id));
        return "client/laptop";
    }
}
