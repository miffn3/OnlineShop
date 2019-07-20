package net.thumbtack.onlineshop.service.impl;

import net.thumbtack.onlineshop.dto.request.ProductDto;
import net.thumbtack.onlineshop.entity.Category;
import net.thumbtack.onlineshop.entity.Product;
import net.thumbtack.onlineshop.repository.iface.ProductRepository;
import net.thumbtack.onlineshop.service.iface.CategoryService;
import net.thumbtack.onlineshop.service.iface.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public Product addProduct(ProductDto productDto) {
        Product product = new Product();
        product.setCount(productDto.getCount());
        product.setPrice(productDto.getPrice());
        product.setName(productDto.getName());
        Set<Category> categories = new HashSet<>();
        for (Long categoryId:productDto.getCategories()) {
            categories.add(categoryService.getCategory(categoryId));
        }
        product.setCategories(categories);
        return productRepository.save(product);
    }

    @Override
    public void updateProduct(Product product) {

    }


    @Override
    public void deleteProduct(Long id){
//        productRepository.deleteProduct(id);
    }

    @Override
    public Product getProduct(Long id){
//        Product product = productRepository.getProductById(id);
//        List<Category> categories = categoryService.getAllProductCategoriesById(id);
//        if (categories.size() != 0) {
//            product.setCategories(categories);
//        }
//        return product;
        return null;
    }

    @Override
    public List<Product> getAllProducts(){
//        List<Product> products = productRepository.getAllProducts();
//        for (Long i = 0; i< products.size(); i++) {
//           List<Category> categories = categoryService.getAllProductCategoriesById(products.get(i).getId());
//            if (categories.size() != 0) {
//                Product tmp = products.get(i);
//                tmp.setCategories(categories);
//                products.set(i,tmp);
//            }
//        }
//        return products;
        return null;
    }
}
