package com.example.sirTalion.service;

import org.springframework.data.domain.Page;

import com.example.sirTalion.entities.Category;

public interface CategoryService
{
    Page<Category> findAllBy(int page, int limit);
    Category findById(long id);
    void saveOrUpdate(Category category);
    void deleteById(long id);
}