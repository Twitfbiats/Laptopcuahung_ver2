package com.example.sirTalion.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.sirTalion.entities.Cart;
import com.example.sirTalion.entities.CartInfo;
import com.example.sirTalion.entities.Product;
import com.example.sirTalion.entities.User;
import com.example.sirTalion.oauth.CustomOAuth2User;
import com.example.sirTalion.repository.CartInfoRepository;
import com.example.sirTalion.service.CartService;
import com.example.sirTalion.service.ProductService;
import com.example.sirTalion.service.UserService;

@Controller
@SessionAttributes("loggedInUser")
@RequestMapping("/")
public class ClientController 
{
    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartInfoRepository cartInfoRepository;
    
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

    @GetMapping("/cart")
    public String cartPage(Model products, @CookieValue(name = "cart", defaultValue = "") String cart, HttpServletResponse response)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Product> products_temp = new ArrayList<>();
        
        if (authentication.getPrincipal().toString().equals("anonymousUser"))
        {
            String[] products_id = cart.split("-");
            
            for (int i=1;i<products_id.length;i++)
            {
                String[] temp = products_id[i].split(":");
                products_temp.add(productService.findById(Long.parseLong(temp[0])));
            }
        }
        else
        {
            User user = userService.findByEmail(authentication.getName());
            if (user.getCart() == null)
            {
                Cart cart1 = new Cart();
                cartService.save(cart1);

                String[] products_id = cart.split("-");
            
                for (int i=1;i<products_id.length;i++)
                {
                    String[] temp = products_id[i].split(":");
                    products_temp.add(productService.findById(Long.parseLong(temp[0])));

                    Product product1 = new Product(Long.parseLong(temp[0]));
                    CartInfo cartInfo1 = new CartInfo(cart1.getId(), product1.getId());
                    cartInfo1.setQuantity(Integer.parseInt(temp[1]));
                    cartInfoRepository.save(cartInfo1);
                }
                user.setCart(cart1);
                userService.updateUser(user);
            }
            else
            {
                Cart cart1 = user.getCart(); 

                String[] products_id = cart.split("-");
                List<CartInfo> cartInfos = cartInfoRepository.findAllByCartId(cart1.getId());

                for (int i=1;i<products_id.length;i++)
                {
                    String[] temp = products_id[i].split(":");
                    boolean flag = false;
                    long product_id_we_need = Long.parseLong(temp[0]);

                    for (CartInfo ci : cartInfos)
                    {
                        if (ci.getId().getProductId() == product_id_we_need) 
                        {ci.setQuantity(ci.getQuantity() + Integer.parseInt(temp[1])); flag = true;}
                    }
                    if (!flag) cartInfos.add(new CartInfo(cart1.getId(), product_id_we_need, Integer.parseInt(temp[1])));
                }

                cartInfos = cartInfoRepository.saveAll(cartInfos);
                for (CartInfo ci : cartInfos) products_temp.add(ci.getProduct());
            }

            Cookie deleteCart = new Cookie("cart", ""); 
            deleteCart.setMaxAge(0);
            response.addCookie(deleteCart);
        }   
        
        products.addAttribute("cart", products_temp);
        products.addAttribute("checkEmpty", products_temp.size());

        
        System.out.println("-------Important: " + authentication.getPrincipal());

        return "client/cart";
    }

    @GetMapping("/home")
    public String homePage()
    {
        return "client/home";
    }
}
