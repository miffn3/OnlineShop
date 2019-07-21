package net.thumbtack.onlineshop.config;

import net.thumbtack.onlineshop.repository.iface.*;
import net.thumbtack.onlineshop.service.iface.*;
import net.thumbtack.onlineshop.service.impl.*;
import net.thumbtack.onlineshop.validation.validator.DuplicateLoginConstraintValidator;
import net.thumbtack.onlineshop.validation.validator.IncorrectLoginPasswordConstraintValidator;
import net.thumbtack.onlineshop.validation.validator.IncorrectPasswordConstraintValidator;
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
    public SessionService sessionService(SessionRepository sessionRepository,
                                         AdministratorService administratorService, ClientService clientService) {
        return new SessionServiceImpl(sessionRepository, administratorService, clientService);
    }

    @Bean
    public AccountService accountService(AdministratorService administratorService, ClientService clientService) {
        return new AccountServiceImpl(administratorService, clientService);
    }


    @Bean
    public ClientService clientService(ClientRepository clientRepository) {
        return new ClientServiceImpl(clientRepository);
    }

    @Bean
    public DuplicateLoginConstraintValidator duplicateLoginConstraintValidator(AdministratorService administratorService,
                                                                            ClientService clientService) {
        return new DuplicateLoginConstraintValidator(administratorService, clientService);
    }

    @Bean
    public IncorrectLoginPasswordConstraintValidator incorrectLoginPasswordConstraintValidator(AdministratorService administratorService,
                                                                                          ClientService clientService) {
        return new IncorrectLoginPasswordConstraintValidator(administratorService, clientService);
    }

    @Bean
    public IncorrectPasswordConstraintValidator incorrectPasswordConstraintValidator(AdministratorService administratorService,
                                                                                     ClientService clientService) {
        return new IncorrectPasswordConstraintValidator(administratorService, clientService);
    }
}
