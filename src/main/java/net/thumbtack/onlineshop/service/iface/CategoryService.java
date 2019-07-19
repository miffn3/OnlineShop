package net.thumbtack.onlineshop.service.iface;

import net.thumbtack.onlineshop.entity.Category;

import java.util.List;

public interface CategoryService {
    int addCategory(Category category);
    Category getCategory(int id);
    void updateCategory(Category category);
    void deleteCategory(int id);
    List<Category> getAllCategories();
    List<Category> getAllProductCategoriesById(int id);
}
