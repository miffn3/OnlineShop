package net.thumbtack.onlineshop.repository.iface;

import net.thumbtack.onlineshop.entity.Category;

import java.util.List;

public interface CategoryRepository {
    int addCategory(Category category);
    Category getCategoryById(int id);
    void deleteCategory(int id);
    void updateCategory(Category category);
    List<Category> getAllCategories();
    List<Category> getAllCategoriesById(int id);
}
