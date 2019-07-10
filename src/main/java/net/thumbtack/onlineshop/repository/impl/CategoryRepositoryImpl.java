package net.thumbtack.onlineshop.repository.impl;

import net.thumbtack.onlineshop.entity.Category;
import net.thumbtack.onlineshop.repository.iface.CategoryRepository;
import net.thumbtack.onlineshop.repository.mapper.CategoryMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CategoryRepositoryImpl implements CategoryRepository {
    private JdbcTemplate jdbcTemplate;
    private CategoryMapper categoryMapper;

    public CategoryRepositoryImpl(JdbcTemplate jdbcTemplate, CategoryMapper categoryMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.categoryMapper = categoryMapper;
    }

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
        jdbcTemplate.update("DELETE FROM category WHERE id=?", id);
    }

    @Override
    public void updateCategory(Category category){
        if (category == null) {
            throw new IllegalArgumentException();
        }
        jdbcTemplate.update("UPDATE category SET" +
                        "name = ?, parentName = ?, parentId = ? WHERE id = ?",
                category.getName(),
                category.getParentName(),
                category.getParentId(),
                category.getId());
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM administrator");
        rows.forEach(row -> {
            Category category = new Category();
            category.setId((int)row.get("id"));
            category.setName((String)row.get("name"));
            category.setParentName((String)row.get("parentName"));
            category.setParentId((int)row.get("parentId"));
            categories.add(category);
        });

        return categories;
    }
}
