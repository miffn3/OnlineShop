package net.thumbtack.onlineshop.repository.extractor;

import net.thumbtack.onlineshop.entity.Category;
import net.thumbtack.onlineshop.entity.Product;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsExtractor implements ResultSetExtractor<List<Product>> {

    public List<Product> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Integer, Product> map = new HashMap<>();

        while (rs.next()) {
            Integer id = rs.getInt("id");
            Product product = map.get(id);
            if(product== null){
                product = new Product();
                product.setId(id);
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setCount(rs.getInt("count"));
                map.put(id, product);
            }
            List categories = product.getCategories();
            if(categories == null) {
                categories = new ArrayList<Category>();
                product.setCategories(categories);
            }
            Category category = new Category();
            category.setId(rs.getInt("category_Id"));
            category.setName(rs.getString("categoryName"));
            category.setParentId(rs.getInt("parentId"));
            category.setParentName(rs.getString("parentName"));
            categories.add(category);
        }
        return new ArrayList<>(map.values());
    }
}
