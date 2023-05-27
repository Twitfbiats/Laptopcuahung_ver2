package com.example.sirTalion.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Category 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(max = 50, message = "Category name should be less than 50 characters")
    @NotEmpty(message = "name can't be empty")
    private String name;

    // @OneToMany(mappedBy = "category")
    // private List<Product> products;
}
