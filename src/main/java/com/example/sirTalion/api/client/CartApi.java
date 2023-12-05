package com.example.sirTalion.api.client;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartApi 
{
    @GetMapping("/{id}")
    public ResponseEntity<String> addToCart(@PathVariable long id, HttpServletRequest request,
    HttpServletResponse response, @CookieValue(name = "cart", defaultValue = "") String cart)
    {
        if (!cart.equals(""))
        {
            if (cart.indexOf("|"+id+":") == -1) 
            {
                Cookie cookie = new Cookie("cart", cart + "|" + id + ":1");
                cookie.setPath("/");
                response.addCookie(cookie);
                return ResponseEntity.ok("");
            }
        }
        else
        {
            Cookie cookie = new Cookie("cart", "|" + id + ":1");
            cookie.setPath("/");
            response.addCookie(cookie);
            return ResponseEntity.ok("");
        }
        
        // System.out.println("Important: " + authentication.getPrincipal().toString());
        // addCookie
        return null;
    }
}
