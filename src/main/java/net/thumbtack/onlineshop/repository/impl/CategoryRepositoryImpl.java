package net.thumbtack.onlineshop.repository.impl;

import net.thumbtack.onlineshop.entity.Category;
import net.thumbtack.onlineshop.repository.iface.CategoryRepository;
import net.thumbtack.onlineshop.repository.mapper.CategoryMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {
    private JdbcTemplate jdbcTemplate;
    private CategoryMapper categoryMapper;

    @Override
    public void addCategory(Category category){

    }

    @Override
    public Category getCategoryById(int id){
        return null;
    }

    @Override
    public void deleteCategory(int id){

    }

    @Override
    public void updateCategory(Category category){

    }

    @Override
    public List<Category> getAllCategories() {
        return null;
    }
}
