package net.thumbtack.onlineshop.service.iface;

import net.thumbtack.onlineshop.entity.Product;

import java.util.List;

public interface ProductService {
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(int id);
    Product getProduct(int id);
    List<Product> getAllProducts();
}
