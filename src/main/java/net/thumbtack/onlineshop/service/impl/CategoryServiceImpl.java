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
    public int addCategory(Category category) {
        return categoryRepository.addCategory(category);
    }

    @Override
    public Category getCategory(int id){
        return categoryRepository.getCategoryById(id);
    }

    @Override
    public void updateCategory(Category category){
        categoryRepository.updateCategory(category);
    }

    @Override
    public void deleteCategory(int id) {
        categoryRepository.deleteCategory(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.getAllCategories();
    }

    @Override
    public List<Category> getAllProductCategoriesById(int id) {
        return categoryRepository.getAllCategoriesById(id);
    }
}
