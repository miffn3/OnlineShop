package net.thumbtack.onlineshop.service.impl;

import net.thumbtack.onlineshop.entity.Category;
import net.thumbtack.onlineshop.repository.iface.CategoryRepository;
import net.thumbtack.onlineshop.service.iface.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category addCategory(Category category) {
        return null;
    }

    @Override
    public Category getCategory(Long id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public void updateCategory(Category category) {

    }

    @Override
    public void deleteCategory(Long id) {

    }

    @Override
    public List<Category> getAllCategories() {
        return null;
    }

    @Override
    public List<Category> getAllProductCategoriesById(Long id) {
        return null;
    }
}
