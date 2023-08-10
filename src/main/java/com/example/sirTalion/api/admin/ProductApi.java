package com.example.sirTalion.api.admin;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.sirTalion.entities.Battery;
import com.example.sirTalion.entities.Category;
import com.example.sirTalion.entities.Cpu;
import com.example.sirTalion.entities.Display;
import com.example.sirTalion.entities.Drive;
import com.example.sirTalion.entities.GraphicCard;
import com.example.sirTalion.entities.Manufacturer;
import com.example.sirTalion.entities.OperatingSystem;
import com.example.sirTalion.entities.Product;
import com.example.sirTalion.entities.Ram;
import com.example.sirTalion.repository.ProductRepository;
import com.example.sirTalion.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/product")
public class ProductApi 
{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired ServletContext servletContext;

    @PersistenceContext
    private EntityManager entityManager;

    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = factory.getValidator();

    // @Autowired
    // private SessionFactory sessionFactory;
    @GetMapping("/latest")
    public List<Product> getLastestProductByCategory(@RequestParam String category)
    {
        return productService.findLatestProduct(category);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Page<Product>> getAllProduct(@RequestParam int page)
    {
        Page<Product> products = productRepository.findAllBy(PageRequest.of(page-1, 5));
        HttpHeaders headers = new HttpHeaders();
        headers.add("get-all-total", products.getTotalElements() + "");

        return new ResponseEntity<>(products, headers, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<Page<Product>> getWithFilter(@RequestParam int page,
    @RequestParam(required = false) String category, @RequestParam(required = false) String manufacturer,
    @RequestParam(required = false) double priceMin, @RequestParam(required = false) double priceMax,
    @RequestParam(required = false) String priceSort)
    {
        Page<Product> products = productService.findProductByFilter(page-1, 5, category, manufacturer, priceMin, priceMax, priceSort);
        HttpHeaders headers = new HttpHeaders();
        headers.add("get-filter-total", products.getTotalElements() + "");

        return new ResponseEntity<>(products, headers, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public Product getProduct(@PathVariable long id)
    {
        return productRepository.findById(id);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/update")
    public ResponseEntity<String> updateProduct(@Valid @RequestBody Product product)
    {
        productRepository.save(product);
        return ResponseEntity.ok("{\"ok\": \"true\"}");
    }

    @PostMapping(value = "/save")
    public ResponseEntity<String> saveProduct(@RequestPart String product_temp, 
    @RequestPart List<MultipartFile> images, @RequestPart List<MultipartFile> images360)
    {
        Product product = new Product();
        try 
        {
            product = new ObjectMapper().readValue(product_temp, Product.class);
            validate(product);
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        List<Cpu> cpus = new ArrayList<>();
        product.getLaptop().getCpus().forEach(cpu -> 
        {
            if (cpu.getManufacturer() != null)
            {
                Manufacturer cpu_manufacturer = entityManager.find(Manufacturer.class, cpu.getManufacturer().getId());
                if (cpu_manufacturer != null) cpu.setManufacturer(cpu_manufacturer);
            }
            
            Cpu cpu_temp = entityManager.find(Cpu.class, cpu.getId());
            if (cpu_temp != null) cpus.add(cpu_temp);
            else cpus.add(cpu);
        });
        product.getLaptop().setCpus(cpus);

        List<Ram> rams = new ArrayList<>();
        product.getLaptop().getRams().forEach(ram -> 
        {
            if (ram.getManufacturer() != null)
            {
                Manufacturer ram_manufacturer = entityManager.find(Manufacturer.class, ram.getManufacturer().getId());
                if (ram_manufacturer != null) ram.setManufacturer(ram_manufacturer);
            }
            
            Ram ram_temp = entityManager.find(Ram.class, ram.getId());
            if (ram_temp != null) rams.add(ram_temp);
            else rams.add(ram);
        });
        product.getLaptop().setRams(rams);

        List<Drive> drives = new ArrayList<>();
        product.getLaptop().getDrives().forEach(drive -> 
        {
            if (drive.getManufacturer() != null)
            {
                Manufacturer driveManufacturer = entityManager.find(Manufacturer.class, drive.getManufacturer().getId());
                if (driveManufacturer != null) drive.setManufacturer(driveManufacturer);
            }
            
            Drive driveTemp = entityManager.find(Drive.class, drive.getId());
            if (driveTemp != null) drives.add(driveTemp);
            else drives.add(drive);
        });
        product.getLaptop().setDrives(drives);

        List<GraphicCard> graphicCards = new ArrayList<>();
        product.getLaptop().getGraphicCards().forEach(graphicCard -> 
        {
            if (graphicCard.getManufacturer() != null)
            {
                Manufacturer graphicCardManufacturer = entityManager.find(Manufacturer.class, graphicCard.getManufacturer().getId());
                if (graphicCardManufacturer != null) graphicCard.setManufacturer(graphicCardManufacturer);
            }

            GraphicCard graphicCardTemp = entityManager.find(GraphicCard.class, graphicCard.getId());
            if (graphicCardTemp != null) graphicCards.add(graphicCardTemp);
            else graphicCards.add(graphicCard);
        });
        product.getLaptop().setGraphicCards(graphicCards);

        if (product.getLaptop().getDisplay() != null)
        {
            Display display = entityManager.find(Display.class, product.getLaptop().getDisplay().getId());
            if (display != null) product.getLaptop().setDisplay(display);
        }
        if (product.getLaptop().getOperatingSystem() != null)
        {
            OperatingSystem operatingSystem = entityManager.find(OperatingSystem.class, product.getLaptop().getOperatingSystem().getId());
            if (operatingSystem != null) product.getLaptop().setOperatingSystem(operatingSystem);
        }
        if (product.getLaptop().getBattery() != null)
        {
            Battery battery = entityManager.find(Battery.class, product.getLaptop().getBattery().getId());
            if (battery != null) product.getLaptop().setBattery(battery);
        }
        if (product.getLaptop().getManufacturer() != null)
        {
            Manufacturer manufacturer = entityManager.find(Manufacturer.class, product.getLaptop().getManufacturer().getId());
            if (manufacturer != null) product.getLaptop().setManufacturer(manufacturer);
        }
        if (product.getCategory() != null)
        {
            Category category = entityManager.find(Category.class, product.getCategory().getId());
            if (category != null) product.setCategory(category);
        }
        
        long id = productRepository.save(product).getId();
        try 
        {           
            String uploadPath = servletContext.getRealPath("resources/images/");
            File file = new File(uploadPath + "product" + id);
            file.mkdir();
            uploadPath = servletContext.getRealPath("resources/images/product" + id);
            for (MultipartFile image : images)
            {
                Path path = Paths.get(uploadPath, image.getOriginalFilename());
                Files.write(path, image.getBytes());
            }    

            uploadPath = servletContext.getRealPath("resources/images/");
            file = new File(uploadPath + "product" + id + "_360");
            file.mkdir();
            uploadPath = servletContext.getRealPath("resources/images/product" + id + "_360");
            for (MultipartFile image : images360)
            {
                Path path = Paths.get(uploadPath, image.getOriginalFilename());
                Files.write(path, image.getBytes());
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }

        return ResponseEntity.ok("{\"ok\": \"true\", \"id\" : \"" + id + "\"}");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable long id)
    {
        productRepository.deleteById(id);

        return ResponseEntity.ok("{\"ok\": \"true\"}");
    }

    @PostMapping("/upload-images/{id}")
    public void uploadProductImages(@RequestParam List<MultipartFile> images, @PathVariable long id)
    {
        try 
        {           
            String uploadPath = servletContext.getRealPath("resources/images/");
            File file = new File(uploadPath + "product" + id);
            file.mkdir();
            uploadPath = servletContext.getRealPath("resources/images/product" + id);
            for (MultipartFile image : images)
            {
                Path path = Paths.get(uploadPath, image.getOriginalFilename());
                Files.write(path, image.getBytes());
            }    
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
    MethodArgumentNotValidException ex) 
    {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    <T> void validate(T t) 
    {
        Set<ConstraintViolation<T>> violations = validator.validate(t);
        if (!violations.isEmpty()) 
        {
            throw new ConstraintViolationException(violations);
        }
    }
}
