package net.thumbtack.onlineshop.service.iface;

import net.thumbtack.onlineshop.dto.request.CategoryCreateRequestDto;
import net.thumbtack.onlineshop.dto.request.CategoryUpdateRequestDto;
import net.thumbtack.onlineshop.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface CategoryService {
    Category addCategory(CategoryCreateRequestDto createRequestDto);
    Category getCategory(Long id);
    void updateCategory(CategoryUpdateRequestDto updateRequestDto);
    void deleteCategory(Long id);
    Set<Category> getAllCategories();
    Set<Category> getAllProductCategoriesById(Long id);
}
