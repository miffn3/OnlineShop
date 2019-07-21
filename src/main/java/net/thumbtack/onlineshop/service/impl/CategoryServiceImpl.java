package net.thumbtack.onlineshop.service.impl;

import net.thumbtack.onlineshop.dto.request.CategoryCreateRequestDto;
import net.thumbtack.onlineshop.dto.request.CategoryUpdateRequestDto;
import net.thumbtack.onlineshop.entity.Category;
import net.thumbtack.onlineshop.repository.iface.CategoryRepository;
import net.thumbtack.onlineshop.service.iface.CategoryService;

import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category addCategory(CategoryCreateRequestDto createRequestDto) {
        Category category = new Category();
        category.setName(createRequestDto.getName());
        category.setParentId(createRequestDto.getParentId());
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategory(Long id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public void updateCategory(CategoryUpdateRequestDto updateRequestDto) {

    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Set<Category> getAllCategories() {
        return null;
    }

    @Override
    public Set<Category> getAllProductCategoriesById(Long id) {
        return null;
    }
}
