package net.thumbtack.onlineshop.repository.mapper;

import net.thumbtack.onlineshop.entity.Category;
import net.thumbtack.onlineshop.entity.Product;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = new Product();
        while (resultSet.next()) {
            product.setId(resultSet.getInt("id"));
            product.setName(resultSet.getString("name"));
            product.setPrice(resultSet.getInt("price"));
            product.setCount(resultSet.getInt("count"));
            Category category = new Category();
            category.setId(resultSet.getInt("category_Id"));
            category.setName(resultSet.getString("categoryName"));
            category.setParentId(resultSet.getInt("parentId"));
            category.setParentName(resultSet.getString("parentName"));
            List<Category> categories = product.getCategories();
            categories.add(category);
            product.setCategories(categories);
        }
        return product;
    }
}
