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

    Category updateCategory(CategoryUpdateRequestDto updateRequestDto, Long id);

    void deleteCategory(Long id);

    Set<Category> getAllCategories();
}
