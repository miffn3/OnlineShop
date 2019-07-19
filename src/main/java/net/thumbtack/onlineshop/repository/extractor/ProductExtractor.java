package net.thumbtack.onlineshop.repository.extractor;

import net.thumbtack.onlineshop.entity.Category;
import net.thumbtack.onlineshop.entity.Product;
import net.thumbtack.onlineshop.repository.mapper.CategoryMapper;
import net.thumbtack.onlineshop.repository.mapper.ProductMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductExtractor implements ResultSetExtractor<Product> {
    private ProductMapper productMapper;
    private CategoryMapper categoryMapper;

    public ProductExtractor(ProductMapper productMapper, CategoryMapper categoryMapper) {
        this.productMapper = productMapper;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Product extractData(ResultSet rs)
            throws SQLException, DataAccessException {
        Product product = null;
        int row = 0;

        while (rs.next()) {
            if(product == null) {
                product = productMapper.mapRow(rs, row);
                List<Category> categories = new ArrayList<>();
                product.setCategories(categories);
            }
            product.addCategory(categoryMapper.mapRow(rs,row));
            row++;
        }

        return product;
    }
}



