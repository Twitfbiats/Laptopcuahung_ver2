package com.example.sirTalion.api.admin;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.sirTalion.entities.Manufacturer;
import com.example.sirTalion.service.ManufacturerService;

@RestController()
@RequestMapping("/api/manufacturer")
public class ManufacturerApi 
{
    @Autowired
    private ManufacturerService manufacturerService;

    @GetMapping("/get-all")
    public Page<Manufacturer> getAllManufacturer(@RequestParam int page)
    {
        return manufacturerService.findAllBy(page, 5);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateManufacturer(@Valid @RequestBody Manufacturer manufacturer)
    {
        manufacturerService.saveOrUpdate(manufacturer);
        return ResponseEntity.ok("{\"ok\": \"true\"}");
    }

    @PostMapping("/add")
    public ResponseEntity<String> addManufacturer(@Valid @RequestBody Manufacturer manufacturer)
    {
        manufacturerService.saveOrUpdate(manufacturer);
        return ResponseEntity.ok("{\"ok\": \"true\"}");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteManufacturer(@PathVariable long id)
    {
        manufacturerService.deleteById(id);
        return ResponseEntity.ok("{\"ok\": \"true\"}");
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
