package net.thumbtack.onlineshop.config;

import com.google.gson.Gson;
import net.thumbtack.onlineshop.repository.extractor.ProductExtractor;
import net.thumbtack.onlineshop.repository.extractor.ProductsExtractor;
import net.thumbtack.onlineshop.repository.iface.*;
import net.thumbtack.onlineshop.repository.impl.*;
import net.thumbtack.onlineshop.repository.mapper.AdministratorMapper;
import net.thumbtack.onlineshop.repository.mapper.CategoryMapper;
import net.thumbtack.onlineshop.repository.mapper.ClientMapper;
import net.thumbtack.onlineshop.repository.mapper.ProductMapper;
import net.thumbtack.onlineshop.repository.mapper.SessionMapper;
import net.thumbtack.onlineshop.service.iface.*;
import net.thumbtack.onlineshop.service.impl.*;
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
    public SessionMapper sessionMapper() {
        return new SessionMapper();
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
        return new ProductRepositoryImpl(jdbcTemplate,  productMapper);
    }

    @Bean
    public ProductService productService(ProductRepository productRepository ,
                                         CategoryService categoryService) {
        return new ProductServiceImpl(productRepository, categoryService);
    }

    @Bean
    public SessionRepository sessionRepository(JdbcTemplate jdbcTemplate, SessionMapper sessionMapper) {
        return new SessionRepositoryImpl(jdbcTemplate, sessionMapper);
    }

    @Bean
    public SessionService sessionService(SessionRepository sessionRepository, AdministratorService administratorService) {
        return new SessionServiceImpl(sessionRepository, administratorService);
    }

    @Bean
    public AccountService accountService(AdministratorService administratorService, ClientService clientService) {
        return new AccountServiceImpl(administratorService, clientService);
    }

    @Bean
    public ClientRepository clientRepository(JdbcTemplate jdbcTemplate, ClientMapper clientMapper) {
        return new ClientRepositoryImpl(jdbcTemplate, clientMapper);
    }

    @Bean
    public ClientService clientService(ClientRepository clientRepository) {
        return new ClientServiceImpl(clientRepository);
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }
}
