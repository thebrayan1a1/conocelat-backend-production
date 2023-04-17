package com.example.conocemelat.service;

import com.example.conocemelat.model.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> listAllCategories();

    public Category saveCategory(Category category);

    public Category getCategoryById(Long id);

    public Category updateCategory(Category category);

    public void deleteCategory(Long id);
}
