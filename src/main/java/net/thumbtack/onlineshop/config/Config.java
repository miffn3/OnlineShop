package net.thumbtack.onlineshop.config;

import net.thumbtack.onlineshop.repository.mapper.AdministratorMapper;
import net.thumbtack.onlineshop.repository.mapper.CategoryMapper;
import net.thumbtack.onlineshop.repository.mapper.ClientMapper;
import net.thumbtack.onlineshop.repository.mapper.ProductMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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


}
