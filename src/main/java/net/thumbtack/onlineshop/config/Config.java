package net.thumbtack.onlineshop.config;

import net.thumbtack.onlineshop.repository.iface.AdministratorRepository;
import net.thumbtack.onlineshop.repository.impl.AdministratorRepositoryImpl;
import net.thumbtack.onlineshop.repository.mapper.AdministratorMapper;
import net.thumbtack.onlineshop.repository.mapper.CategoryMapper;
import net.thumbtack.onlineshop.repository.mapper.ClientMapper;
import net.thumbtack.onlineshop.repository.mapper.ProductMapper;
import net.thumbtack.onlineshop.service.iface.AdministratorService;
import net.thumbtack.onlineshop.service.impl.AdministratorServiceImpl;
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
    public AdministratorRepository personRepository(JdbcTemplate jdbcTemplate, AdministratorMapper administratorMapper) {
        return new AdministratorRepositoryImpl(jdbcTemplate, administratorMapper);
    }

    @Bean
    public AdministratorService administratorService(AdministratorRepository administratorRepository) {
        return new AdministratorServiceImpl(administratorRepository);
    }
}
