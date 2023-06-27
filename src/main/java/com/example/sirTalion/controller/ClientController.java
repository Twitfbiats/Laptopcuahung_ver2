package com.example.sirTalion.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.sirTalion.entities.User;
import com.example.sirTalion.oauth.CustomOAuth2User;
import com.example.sirTalion.service.UserService;

@Controller
@SessionAttributes("loggedInUser")
@RequestMapping("/")
public class ClientController 
{
    @Autowired
    private UserService userService;
    
    @ModelAttribute("loggedInUser")
    public User loggedInUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = null;
        if ((user = userService.findByEmail(authentication.getName())) != null)
        {
            return user;
        }
        try 
        {
            return 
            userService.findByEmail
            (
                ((CustomOAuth2User)authentication.getPrincipal()).getEmail()
            );
        } 
        catch (Exception e) 
        {
            System.out.println("Can't not cast CustomOauth2User to ?????");
        }
        
        return null;
    }

    @GetMapping
    public String laptopCuaHung()
    {
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String loginPage()
    {
        return "client/login";
    }

    @GetMapping("/register")
    public String registerPage(Model model)
    {
        if (model.getAttribute("newUser") == null) model.addAttribute("newUser", new User());
        return "client/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute User newUser, BindingResult bindingResult, Model model)
    {
        List<String> errorMsgs = new ArrayList<>();
        if (bindingResult.hasErrors())
        {
            model.addAttribute("newUser", newUser);
            
            List<ObjectError> listTemp = bindingResult.getAllErrors();
            for (ObjectError oe : listTemp)
            {
                errorMsgs.add(oe.getDefaultMessage());
            }
            model.addAttribute("errors", errorMsgs);
            return "client/register";
        }

        if (!userService.saveUser(newUser))
        {
            model.addAttribute("newUser", newUser);
            errorMsgs.add("User with email already exist");
            model.addAttribute("errors", errorMsgs);
            return "client/register";
        }
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String homePage()
    {
        return "client/home";
    }
}
