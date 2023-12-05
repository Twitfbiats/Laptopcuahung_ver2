package com.example.sirTalion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.sirTalion.entities.Product;
import com.example.sirTalion.entities.QProduct;
import com.example.sirTalion.repository.ProductRepository;
import com.example.sirTalion.service.ProductService;
import com.querydsl.core.BooleanBuilder;

@Service
public class ProductServiceImpl implements ProductService
{
    @Autowired
    private ProductRepository productRepository;

    public Page<Product> findProductByFilter(int page, int limit, String category, String manufacturer,
    double priceMin, double priceMax, String priceSort)
    {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (category != null && !category.equals(""))
        {
            booleanBuilder.and(QProduct.product.category.name.eq(category));

            if (manufacturer != null && !manufacturer.equals(""))
            {
                if (category.equals("laptop"))
                {
                    booleanBuilder.and(QProduct.product.laptop.manufacturer.name.eq(manufacturer));
                }
            }
        }

        booleanBuilder.and(QProduct.product.price.between(priceMin, priceMax));

        return productRepository.findAll(booleanBuilder, PageRequest.of(page, limit, Sort.by(Direction.fromString(priceSort), "price")));
    }

    @Override
    public List<Product> findLatestProduct(String category) 
    {
        return productRepository.findFirst12ByCategoryNameContainingIgnoreCaseOrderByIdDesc(category);
    }

    public Product findById(Long id)
    {
        return productRepository.findById(id).get();
    }
}
