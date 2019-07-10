package net.thumbtack.onlineshop.repository.impl;

import net.thumbtack.onlineshop.entity.Category;
import net.thumbtack.onlineshop.repository.iface.CategoryRepository;
import net.thumbtack.onlineshop.repository.mapper.CategoryMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {
    private JdbcTemplate jdbcTemplate;
    private CategoryMapper categoryMapper;

    @Override
    public void addCategory(Category category){
        if(category == null) {
            throw new IllegalArgumentException();
        }
        jdbcTemplate.update("INSERT INTO category (name, parentId, parentName) VALUES (?,?,?)",
                category.getName(),
                category.getParentId(),
                category.getParentName());
    }

    @Override
    public Category getCategoryById(int id){
        return jdbcTemplate.queryForObject("SELECT * FROM category WHERE id=?", new Object[]{id}, categoryMapper);
    }

    @Override
    public void deleteCategory(int id){

    }

    @Override
    public void updateCategory(Category category){

    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();

        return null;
    }
}
