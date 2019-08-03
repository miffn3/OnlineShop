package net.thumbtack.onlineshop.service.impl;

import net.thumbtack.onlineshop.dto.request.ProductDto;
import net.thumbtack.onlineshop.entity.Category;
import net.thumbtack.onlineshop.entity.Product;
import net.thumbtack.onlineshop.repository.iface.ProductRepository;
import net.thumbtack.onlineshop.service.iface.CategoryService;
import net.thumbtack.onlineshop.service.iface.ProductService;

import java.util.ArrayList;
import java.util.Collection;
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

        for (Long categoryId : productDto.getCategories()) {
            categories.add(categoryService.getCategory(categoryId));
        }

        product.setCategories(categories);

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(ProductDto productDto, Long id) {
        Product product = productRepository.findById(id).get();
        product.setCount(productDto.getCount());
        product.setPrice(productDto.getPrice());
        product.setName(productDto.getName());

        if (productDto.getCategories() != null) {
            if (productDto.getCategories().size() == 0) {
                product.setCategories(null);

            } else {
                Set<Category> categories = new HashSet<>();
                if (product.getCategories() != null) {
                    categories = product.getCategories();
                }
                for (Long categoryId : productDto.getCategories()) {
                    categories.add(categoryService.getCategory(categoryId));
                }
                product.setCategories(categories);
            }
        }

        return productRepository.save(product);
    }


    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).get();
        productRepository.delete(product);
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>((Collection) productRepository.findAll());
    }
}
