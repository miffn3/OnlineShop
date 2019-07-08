package net.thumbtack.onlineshop.repository.mapper;

import net.thumbtack.onlineshop.entity.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet resultSet, int i) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getInt("id"));
        category.setName(resultSet.getString("name"));
        category.setParentId(resultSet.getInt("parentId"));
        category.setParentName(resultSet.getString("parentName"));
        return category;
    }
}
