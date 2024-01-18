package com.damjan.bookshelf.repository;

import com.damjan.bookshelf.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAllById(Integer categoryId);
    List<Category> findAll();
}
