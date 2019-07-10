package net.thumbtack.onlineshop.repository.impl;

import net.thumbtack.onlineshop.entity.Product;
import net.thumbtack.onlineshop.repository.iface.ProductRepository;
import net.thumbtack.onlineshop.repository.mapper.ProductMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
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
    public void addProduct(Product product){
        if(product == null) {
            throw new IllegalArgumentException();
        }
        jdbcTemplate.update("INSERT INTO product (name, price, count) VALUES (?,?,?)",
                product.getName(),
                product.getPrice(),
                product.getCount());

    }

    @Override
    public Product getProductById(int id){
        return jdbcTemplate.queryForObject("SELECT * FROM product WHERE id=?", new Object[]{id}, productMapper);
    }

    @Override
    public void deleteProduct(int id) {
        jdbcTemplate.update("DELETE FROM product WHERE id=?", id);
    }

    @Override
    public void updateProduct(Product product){

    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM product");
        rows.forEach(row -> {
            Product product = new Product();
            product.setId((long)row.get("id"));
            product.setName((String)row.get("name"));
            product.setPrice((long)row.get("price"));
            product.setCount((long)row.get("count"));
            products.add(product);
        });

        return products;
    }
}
