package net.thumbtack.onlineshop.service.impl;

import net.thumbtack.onlineshop.entity.Product;
import net.thumbtack.onlineshop.repository.iface.ProductRepository;
import net.thumbtack.onlineshop.service.iface.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Product product){
        productRepository.addProduct(product);
    }
    public void updateProduct(Product product){
        productRepository.updateProduct(product);
    }
    public void deleteProduct(int id){
        productRepository.deleteProduct(id);
    }
    public Product getProduct(int id){
        return productRepository.getProductById(id);
    }

    public List<Product> getAllProducts(){
        return productRepository.getAllProducts();
    }
}
