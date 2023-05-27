package com.example.sirTalion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @GetMapping("register")
    public String registerPage()
    {
        return "client/register";
    }

    @GetMapping("/home")
    public String homePage()
    {
        return "client/home";
    }
}
