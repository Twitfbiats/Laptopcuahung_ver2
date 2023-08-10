package com.example.sirTalion.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.example.sirTalion.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product>
{
    // Product save(Product product);
    Product findById(long id);
    Page<Product> findAllBy(Pageable pageable);
    List<Product> findFirst12ByCategoryNameContainingIgnoreCaseOrderByIdDesc(String category);
}
