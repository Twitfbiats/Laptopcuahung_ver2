package com.example.sirTalion.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import com.example.sirTalion.entities.Cart;
import com.example.sirTalion.entities.CartInfo;
import com.example.sirTalion.entities.CartInfoKey;
import com.example.sirTalion.entities.Product;
import com.example.sirTalion.entities.User;
import com.example.sirTalion.oauth.CustomOAuth2User;
import com.example.sirTalion.repository.CartInfoRepository;
import com.example.sirTalion.service.CartService;
import com.example.sirTalion.service.ProductService;
import com.example.sirTalion.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

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

    @Autowired
    private RestTemplate restTemplate;

    private JsonParser jsonParser = JsonParserFactory.getJsonParser();
    private ObjectMapper objectMapper = new ObjectMapper();
    
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
            System.out.println("Can not cast CustomOauth2User to ?????");
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
        List<String> quantities = new ArrayList<>(); quantities.add(null);
        
        if (authentication.getPrincipal().toString().equals("anonymousUser"))
        {
            String[] products_id = cart.split("\\|");
            
            for (int i=1;i<products_id.length;i++)
            {
                String[] temp = products_id[i].split(":");
                products_temp.add(productService.findById(Long.parseLong(temp[0])));
                addToListAtIndex(quantities, Integer.parseInt(temp[0]), temp[1]);
            }
        }
        else
        {
            User user = userService.findByEmail(authentication.getName());
            if (user.getCart() == null)
            {
                Cart cart1 = new Cart();
                cart1 = cartService.save(cart1);

                String[] products_id = cart.split("\\|");
            
                for (int i=1;i<products_id.length;i++)
                {
                    String[] temp = products_id[i].split(":");
                    products_temp.add(productService.findById(Long.parseLong(temp[0])));

                    Product product1 = new Product(Long.parseLong(temp[0]));
                    CartInfo cartInfo1 = new CartInfo(cart1.getId(), product1.getId());
                    cartInfo1.setQuantity(Integer.parseInt(temp[1]));
                    cartInfoRepository.save(cartInfo1);

                    addToListAtIndex(quantities, Integer.parseInt(temp[0]), temp[1]);
                }
                user.setCart(cart1);
                userService.updateUser(user);
            }
            else
            {
                Cart cart1 = user.getCart(); 

                String[] products_id = cart.split("\\|");
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
                for (CartInfo ci : cartInfos)
                {
                    products_temp.add(ci.getProduct());
                    addToListAtIndex(quantities, (int)ci.getId().getProductId(), ci.getQuantity() + "");
                }
            }

            Cookie deleteCart = new Cookie("cart", ""); 
            deleteCart.setMaxAge(0);
            response.addCookie(deleteCart);
        }   
        
        products.addAttribute("cart", products_temp);
        products.addAttribute("checkEmpty", products_temp.size());
        products.addAttribute("quantity", quantities);
        products.addAttribute("principal", authentication.getPrincipal());

        
        System.out.println("-------Important: " + authentication.getPrincipal());

        return "client/cart";
    }

    @PostMapping("/cart/change-quantity")
    public ResponseEntity<String> changeCartQuantity(@RequestParam long product, @RequestParam long quantity)
    {
        if (quantity < 0) return new ResponseEntity<> ("{\"requestStatus\": \"UnknownError\"}", HttpStatus.OK);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal().toString().equals("anonymousUser"))
        {
            return new ResponseEntity<> ("{\"requestStatus\": \"anonymous\"}", HttpStatus.OK);
        }
        else
        {
            try 
            {
                User user = userService.findByEmail(authentication.getName());
                CartInfo cartInfo = cartInfoRepository.getReferenceById(new CartInfoKey(user.getCart().getId(), product));
                cartInfo.setQuantity((int)quantity);
                cartInfoRepository.save(cartInfo);

                return new ResponseEntity<> ("{\"requestStatus\": \"ok\"}", HttpStatus.OK);
            } 
            catch (Exception e) // Just in-case user cart is null
            {
                return new ResponseEntity<> ("{\"requestStatus\": \"UnknownError\"}", HttpStatus.OK);
            }
        }
    }

    @GetMapping("/checkout")
    public String checkOut(Model model)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Product> products = new ArrayList<Product>();
        Map<Long, Integer> quantities = new HashMap<>();

        if (authentication.getPrincipal().toString().equals("anonymousUser"))
        {
            return "redirect:/login";
        }
        else
        {
            User user = userService.findByEmail(authentication.getName());
            if (user.getCart() == null)
            {
                return "redirect:/cart";
            }
            else
            {
                Cart cart = user.getCart();
                List<CartInfo> cartInfos = cartInfoRepository.findAllByCartId(cart.getId());

                cartInfos.forEach(cartInfo -> 
                {
                    Product product = productService.findById(cartInfo.getProduct().getId());
                    products.add(product);
                    quantities.put(product.getId(), cartInfo.getQuantity());
                });

                model.addAttribute("products", products);
                model.addAttribute("quantities", quantities);

                return "client/checkout";
            }
        }
    }

    @Value("${paypal.token-url}")
    private String tokenUrl;

    @Value("${paypal.order-url}")
    private String orderUrl;

    @Value("${paypal.client-id}")
    private String clientId;

    @Value("${paypal.client-secret}")
    private String clientSecret;

    @GetMapping("/pay/get-access-token")
    public ResponseEntity<String> paypalAccessToken()
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(clientId, clientSecret);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        RequestEntity<String> requestEntity
        = RequestEntity.post(tokenUrl).headers(headers).body("grant_type=client_credentials");

        var res = restTemplate.exchange(requestEntity, String.class).getBody();
        Map<String, Object> resMap = jsonParser.parseMap(res);

        return ResponseEntity.ok(resMap.get("access_token") + "");
    }

    @GetMapping("/pay/get-client-id")
    public ResponseEntity<String> paypalClientId()
    {
        return ResponseEntity.ok(clientId);
    }

    @GetMapping("/pay/create-order")
    public ResponseEntity<String> paypalCreateOrder()
    {
        var access_token = restTemplate.getForObject("http://localhost:8080/pay/get-access-token", String.class);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(access_token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        ObjectNode root = objectMapper.createObjectNode();
        
        //000000000000000000000000000000000000000000000000000000000
        ArrayNode purchase_units = objectMapper.createArrayNode();

        ObjectNode purchase_unit1 = objectMapper.createObjectNode();
        ObjectNode ammount = objectMapper.createObjectNode();
        ammount.put("currency_code", "USD");
        ammount.put("value", "2000");
        purchase_unit1.set("ammount", ammount);
        purchase_unit1.put("reference_id", "1");
        
        purchase_units.add(purchase_unit1);

        root.set("purchase_units", purchase_units);
        root.put("intent", "CAPTURE");

        System.out.println(root.toPrettyString());

        RequestEntity<String> requestEntity
        = RequestEntity.post(orderUrl).headers(headers).body(root.toPrettyString());

        var res = restTemplate.exchange(requestEntity, String.class).getBody();
        System.out.println(res);

        return null;
    }

    @GetMapping("/home")
    public String homePage()
    {
        return "client/home";
    }

    class Custom
    {
        
    }

    // custom function
    public void addToListAtIndex(List<String> list, int index, String value)
    {
        if (list.size()-1 >= index) list.set(index, value);
        else
        {
            while (list.size()-1 < index) list.add(null);
            list.set(index, value);
        }
    }
}
