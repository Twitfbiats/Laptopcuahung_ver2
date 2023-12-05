package com.example.sirTalion.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.sirTalion.entities.Product;

public interface ProductService 
{
    public Page<Product> findProductByFilter(int page, int limit, String category, String manufacturer, double priceMin,
                                            double priceMax, String priceSort);
    public List<Product> findLatestProduct(String category);
    public Product findById(Long id);
}
