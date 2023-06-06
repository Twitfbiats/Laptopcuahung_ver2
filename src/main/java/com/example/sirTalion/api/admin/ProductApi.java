package com.example.sirTalion.api.admin;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

@RestController
@RequestMapping("/api/product")
public class ProductApi 
{
    @Autowired
    private ProductRepository productRepository;

    @Autowired ServletContext servletContext;

    @PersistenceContext
    private EntityManager entityManager;

    // @Autowired
    // private SessionFactory sessionFactory;

    @GetMapping("/get-all")
    public Page<Product> getAllProduct(@RequestParam int page)
    {
        return productRepository.findAllBy(PageRequest.of(page-1, 5));
    }

    @GetMapping("/get/{id}")
    public Product getProduct(@PathVariable long id)
    {
        return productRepository.findById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveProduct(@Valid @RequestBody Product product)
    {
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
        return ResponseEntity.ok("{\"ok\": \"true\", \"id\" : \"" + id + "\"}");
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
}
