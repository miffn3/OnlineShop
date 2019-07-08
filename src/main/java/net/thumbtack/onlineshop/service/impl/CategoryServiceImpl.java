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

    public void addCategory(Category category) {
        categoryRepository.addCategory(category);
    }
    public Category getCategory(int id){
        return categoryRepository.getCategoryById(id);
    }
    public void updateCategory(Category category){
        categoryRepository.updateCategory(category);
    }
    public void deleteCategory(int id) {
        categoryRepository.deleteCategory(id);
    }
    public List<Category> getAllCategory() {
        return categoryRepository.getAllCategories();
    }
}
