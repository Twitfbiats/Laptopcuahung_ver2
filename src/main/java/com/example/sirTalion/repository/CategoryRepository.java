package com.example.sirTalion.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sirTalion.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>
{
    Page<Category> findAllBy(Pageable pageable);
    List<Category> findAll();
}
