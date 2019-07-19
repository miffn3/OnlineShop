package net.thumbtack.onlineshop.repository.iface;

import net.thumbtack.onlineshop.entity.Product;

import java.util.List;

public interface ProductRepository {
    int addProduct(Product product);
    Product getProductById(int id);
    void deleteProduct(int id);
    void updateProduct(Product product);
    List<Product> getAllProducts();
    void addProductCategory(int productId, int categoryId);
}
