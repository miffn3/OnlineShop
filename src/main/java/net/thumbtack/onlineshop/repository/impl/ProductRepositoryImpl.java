package net.thumbtack.onlineshop.repository.impl;

import net.thumbtack.onlineshop.entity.Product;
import net.thumbtack.onlineshop.repository.extractor.ProductExtractor;
import net.thumbtack.onlineshop.repository.iface.ProductRepository;
import net.thumbtack.onlineshop.repository.mapper.CategoryMapper;
import net.thumbtack.onlineshop.repository.mapper.ProductMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductRepositoryImpl implements ProductRepository {
    private JdbcTemplate jdbcTemplate;
    private ProductMapper productMapper;
    private CategoryMapper categoryMapper;

    public ProductRepositoryImpl(JdbcTemplate jdbcTemplate, ProductMapper productMapper, CategoryMapper categoryMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.productMapper = productMapper;
        this.categoryMapper = categoryMapper;
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
        return jdbcTemplate.query("SELECT product.id AS id, product.name AS name, price, count, " +
                "category.id AS category_Id, category.name AS categoryName, parentId, parentName " +
                "FROM product JOIN product_category ON productId=product.id JOIN category ON category.id=categoryId " +
                "WHERE product.id=?", new ProductExtractor(productMapper,categoryMapper), id);
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
            product.setId((int)row.get("id"));
            product.setName((String)row.get("name"));
            product.setPrice((int)row.get("price"));
            product.setCount((int)row.get("count"));
            products.add(product);
        });

        return products;
    }
}
