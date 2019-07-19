package net.thumbtack.onlineshop.service.iface;

import net.thumbtack.onlineshop.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    Category addCategory(Category category);
    Category getCategory(Long id);
    void updateCategory(Category category);
    void deleteCategory(Long id);
    List<Category> getAllCategories();
    List<Category> getAllProductCategoriesById(Long id);
}
