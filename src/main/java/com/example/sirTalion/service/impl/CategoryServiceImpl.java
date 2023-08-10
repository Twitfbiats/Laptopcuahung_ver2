package com.example.sirTalion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.sirTalion.entities.Category;
import com.example.sirTalion.repository.CategoryRepository;
import com.example.sirTalion.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService
{
    @Autowired
    private CategoryRepository categoryRepository;

    public Page<Category> findAllBy(int page, int limit) 
    {
        return categoryRepository.findAllBy(PageRequest.of(page-1, limit));
    }

    public Category findById(long id) 
    {
        return categoryRepository.findById(id).get();
    }

    public void saveOrUpdate(Category category) 
    {
        categoryRepository.save(category);
    }

    public void deleteById(long id) 
    {
        categoryRepository.deleteById(id);
    }

    public List<Category> findAll()
    {
        return categoryRepository.findAll();
    }
}
