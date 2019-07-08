package net.thumbtack.onlineshop.repository.impl;

import net.thumbtack.onlineshop.entity.Product;
import net.thumbtack.onlineshop.repository.iface.ProductRepository;
import net.thumbtack.onlineshop.repository.mapper.ProductMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {
    private JdbcTemplate jdbcTemplate;
    private ProductMapper productMapper;

    @Override
    public void addProduct(Product product){

    }

    @Override
    public Product getProductById(int id){
        return null;
    }

    @Override
    public void deleteProduct(int id) {

    }

    @Override
    public void updateProduct(Product product){

    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }
}
