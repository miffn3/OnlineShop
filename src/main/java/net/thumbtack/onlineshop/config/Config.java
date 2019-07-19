package net.thumbtack.onlineshop.config;

import com.google.gson.Gson;

import net.thumbtack.onlineshop.repository.iface.AdministratorRepository;
import net.thumbtack.onlineshop.repository.iface.CategoryRepository;
import net.thumbtack.onlineshop.repository.iface.ProductRepository;
import net.thumbtack.onlineshop.service.iface.AdministratorService;
import net.thumbtack.onlineshop.service.iface.CategoryService;
import net.thumbtack.onlineshop.service.iface.ProductService;
import net.thumbtack.onlineshop.service.impl.AdministratorServiceImpl;
import net.thumbtack.onlineshop.service.impl.CategoryServiceImpl;
import net.thumbtack.onlineshop.service.impl.ProductServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public CategoryService categoryService(CategoryRepository categoryRepository) {
        return new CategoryServiceImpl(categoryRepository);
    }

    @Bean
    public ProductService productService(ProductRepository productRepository, CategoryService categoryService) {
        return new ProductServiceImpl(productRepository, categoryService);
    }

    @Bean
    public AdministratorService administratorService(AdministratorRepository administratorRepository) {
        return new AdministratorServiceImpl(administratorRepository);
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }
}
