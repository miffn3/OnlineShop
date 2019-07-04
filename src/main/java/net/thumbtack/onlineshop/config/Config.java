package net.thumbtack.onlineshop.config;

import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.entity.Category;
import net.thumbtack.onlineshop.entity.Client;
import net.thumbtack.onlineshop.entity.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    Administrator administrator() {
        return new Administrator();
    }

    @Bean
    Client client() {
        return new Client();
    }

    @Bean
    Product product() {
        return new Product();
    }

    @Bean
    Category category() {
        return new Category();
    }
}
