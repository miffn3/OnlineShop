package net.thumbtack.onlineshop.config;

import com.google.gson.Gson;
import net.thumbtack.onlineshop.repository.iface.AdministratorRepository;
import net.thumbtack.onlineshop.repository.iface.CategoryRepository;
import net.thumbtack.onlineshop.repository.iface.ProductRepository;
import net.thumbtack.onlineshop.repository.impl.AdministratorRepositoryImpl;
import net.thumbtack.onlineshop.repository.impl.CategoryRepositoryImpl;
import net.thumbtack.onlineshop.repository.impl.ProductRepositoryImpl;
import net.thumbtack.onlineshop.repository.mapper.AdministratorMapper;
import net.thumbtack.onlineshop.repository.mapper.CategoryMapper;
import net.thumbtack.onlineshop.repository.mapper.ClientMapper;
import net.thumbtack.onlineshop.repository.mapper.ProductMapper;
import net.thumbtack.onlineshop.service.iface.AdministratorService;
import net.thumbtack.onlineshop.service.iface.CategoryService;
import net.thumbtack.onlineshop.service.iface.ProductService;
import net.thumbtack.onlineshop.service.iface.SessionService;
import net.thumbtack.onlineshop.service.impl.AdministratorServiceImpl;
import net.thumbtack.onlineshop.service.impl.CategoryServiceImpl;
import net.thumbtack.onlineshop.service.impl.ProductServiceImpl;
import net.thumbtack.onlineshop.service.impl.SessionServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class Config {

    @Bean
    public AdministratorMapper administratorMapper() {
        return new AdministratorMapper();
    }

    @Bean
    public ClientMapper clientMapper() {
        return new ClientMapper();
    }

    @Bean
    public ProductMapper productMapper() {
        return new ProductMapper();
    }

    @Bean
    public CategoryMapper categoryMapper() {
        return new CategoryMapper();
    }

    @Bean
    public AdministratorRepository administratorRepository(JdbcTemplate jdbcTemplate, AdministratorMapper administratorMapper) {
        return new AdministratorRepositoryImpl(jdbcTemplate, administratorMapper);
    }

    @Bean
    public AdministratorService administratorService(AdministratorRepository administratorRepository) {
        return new AdministratorServiceImpl(administratorRepository);
    }

    @Bean
    public CategoryRepository categoryRepository(JdbcTemplate jdbcTemplate, CategoryMapper categoryMapper) {
        return new CategoryRepositoryImpl(jdbcTemplate, categoryMapper);
    }

    @Bean
    public CategoryService categoryService(CategoryRepository categoryRepository) {
        return new CategoryServiceImpl(categoryRepository);
    }

    @Bean
    public ProductRepository productRepository(JdbcTemplate jdbcTemplate, ProductMapper productMapper) {
        return new ProductRepositoryImpl(jdbcTemplate, productMapper);
    }

    @Bean
    public ProductService productService(ProductRepository productRepository) {
        return new ProductServiceImpl(productRepository);
    }

    @Bean
    public SessionService sessionService() {
        return new SessionServiceImpl();
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }
}
