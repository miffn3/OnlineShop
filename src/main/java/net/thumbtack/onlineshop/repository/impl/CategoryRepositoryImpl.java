package net.thumbtack.onlineshop.repository.impl;

import net.thumbtack.onlineshop.entity.Category;
import net.thumbtack.onlineshop.repository.iface.CategoryRepository;
import net.thumbtack.onlineshop.repository.mapper.CategoryMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.ArrayList;
import java.util.HashMap;
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
    public int addCategory(Category category){
        if(category == null) {
            throw new IllegalArgumentException();
        }
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("category").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>(3);
        parameters.put("name", category.getName());
        parameters.put("parentId", category.getParentId());
        parameters.put("parentName", category.getParentName());
        Number newId = simpleJdbcInsert.executeAndReturnKey(parameters);
        return (int) newId;
    }

    @Override
    public Category getCategoryById(int id){
        return jdbcTemplate.queryForObject("SELECT id AS category_Id, name AS categoryName, parentId, parentName FROM category WHERE id=?", new Object[]{id}, categoryMapper);
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

        List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT id AS category_Id, name AS categoryName, parentId, parentName FROM category");
        rows.forEach(row -> {
            Category category = new Category();
            category.setId((int)row.get("category_Id"));
            category.setName((String)row.get("categoryName"));
            category.setParentName((String)row.get("parentName"));
            category.setParentId((int)row.get("parentId"));
            categories.add(category);
        });

        return categories;
    }

    @Override
    public List<Category> getAllCategoriesById(int id) {
        List<Category> categories = new ArrayList<>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT id AS category_Id, name AS categoryName, parentId, parentName " +
                "FROM product_category  JOIN category ON id = categoryId " +
                "WHERE productId=?", id);
        rows.forEach(row -> {
            Category category = new Category();
            category.setId((int)row.get("category_Id"));
            category.setName((String)row.get("categoryName"));
            category.setParentName((String)row.get("parentName"));
            category.setParentId((int)row.get("parentId"));
            categories.add(category);
        });

        return categories;
    }


}
