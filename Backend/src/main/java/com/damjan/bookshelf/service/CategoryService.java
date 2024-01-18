package com.damjan.bookshelf.service;

import com.damjan.bookshelf.model.Category;
import com.damjan.bookshelf.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Transactional
    public Category createCategory(String category) {
        Category categories = new Category();
        categories.setCategory(category);
        return categoryRepository.save(categories);
    }

    @Transactional
    public void updateCategory(Integer id, String category) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category categories = optionalCategory.get();
            categories.setId(id);
            categories.setCategory(category);
            categoryRepository.save(categories);
        } else {
            throw new EntityNotFoundException("Category with id " + id + " not found");
        }
    }

    @Transactional
    public void deleteCategoriesById(Integer id) {
        categoryRepository.deleteById(id);
    }
}