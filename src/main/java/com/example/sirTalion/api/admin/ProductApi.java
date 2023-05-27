package com.example.sirTalion.api.admin;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.sirTalion.entities.Product;
import com.example.sirTalion.repository.ProductRepository;

@RestController
@RequestMapping("/api/product")
public class ProductApi 
{
    @Autowired
    private ProductRepository productRepository;

    @Autowired ServletContext servletContext;

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

    @PostMapping("/upload/{id}")
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
}
