package net.thumbtack.onlineshop.repository.impl;

import net.thumbtack.onlineshop.entity.Product;
import net.thumbtack.onlineshop.repository.extractor.ProductsExtractor;
import net.thumbtack.onlineshop.repository.iface.ProductRepository;
import net.thumbtack.onlineshop.repository.mapper.ProductMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepositoryImpl implements ProductRepository {
    private JdbcTemplate jdbcTemplate;
    private ProductMapper productMapper;

    public ProductRepositoryImpl(JdbcTemplate jdbcTemplate, ProductMapper productMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.productMapper = productMapper;
    }

    @Override
    public int addProduct(Product product){
        if(product == null) {
            throw new IllegalArgumentException();
        }
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("product").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>(3);
        parameters.put("name", product.getName());
        parameters.put("price", product.getPrice());
        parameters.put("count", product.getCount());
        Number newId = simpleJdbcInsert.executeAndReturnKey(parameters);
        return (int) newId;
    }

    @Override
    public Product getProductById(int id){
        return jdbcTemplate.queryForObject("SELECT id, name, price, count " +
                "FROM product WHERE id=?", new Object[]{id}, productMapper);
    }

    @Override
    public void deleteProduct(int id) {
        jdbcTemplate.update("DELETE FROM product WHERE id=?", id);
    }

    @Override
    public void updateProduct(Product product){
        if (product == null) {
            throw new IllegalArgumentException();
        }
        jdbcTemplate.update("UPDATE product SET" +
                " name=?, count=?, price=? WHERE id = ?",
                product.getName(),
                product.getCount(),
                product.getPrice(),
                product.getId());
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM product");
        rows.forEach(row -> {
            Product product = new Product();
            product.setId((int)row.get("id"));
            product.setName((String)row.get("name"));
            product.setPrice((int)row.get("price"));
            product.setCount((int)row.get("count"));
            products.add(product);
        });
        return products;
    }

    @Override
    public void addProductCategory(int productId, int categoryId) {
        jdbcTemplate.update("INSERT INTO product_category (productId, categoryId)  VALUES(?,?)",
                productId,categoryId);
    }
}
