package net.thumbtack.onlineshop.config;

import net.thumbtack.onlineshop.repository.iface.AdministratorRepository;
import net.thumbtack.onlineshop.repository.iface.BasketItemRepository;
import net.thumbtack.onlineshop.repository.iface.CategoryRepository;
import net.thumbtack.onlineshop.repository.iface.ClientRepository;
import net.thumbtack.onlineshop.repository.iface.ProductRepository;
import net.thumbtack.onlineshop.repository.iface.SalesRepository;
import net.thumbtack.onlineshop.repository.iface.SessionRepository;
import net.thumbtack.onlineshop.service.iface.AccountService;
import net.thumbtack.onlineshop.service.iface.AdministratorService;
import net.thumbtack.onlineshop.service.iface.BasketItemService;
import net.thumbtack.onlineshop.service.iface.CategoryService;
import net.thumbtack.onlineshop.service.iface.ClientService;
import net.thumbtack.onlineshop.service.iface.DebugService;
import net.thumbtack.onlineshop.service.iface.ProductService;
import net.thumbtack.onlineshop.service.iface.SalesService;
import net.thumbtack.onlineshop.service.iface.SessionService;
import net.thumbtack.onlineshop.service.impl.AccountServiceImpl;
import net.thumbtack.onlineshop.service.impl.AdministratorServiceImpl;
import net.thumbtack.onlineshop.service.impl.BasketItemServiceImpl;
import net.thumbtack.onlineshop.service.impl.CategoryServiceImpl;
import net.thumbtack.onlineshop.service.impl.ClientServiceImpl;
import net.thumbtack.onlineshop.service.impl.DebugServiceImpl;
import net.thumbtack.onlineshop.service.impl.ProductServiceImpl;
import net.thumbtack.onlineshop.service.impl.SalesServiceImpl;
import net.thumbtack.onlineshop.service.impl.SessionServiceImpl;
import net.thumbtack.onlineshop.util.DeveloperDataInitializer;
import net.thumbtack.onlineshop.validation.validator.DuplicateLoginConstraintValidator;
import net.thumbtack.onlineshop.validation.validator.IncorrectLoginPasswordConstraintValidator;
import net.thumbtack.onlineshop.validation.validator.IncorrectPasswordConstraintValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public DeveloperDataInitializer dataInitializer(AdministratorRepository administratorRepository,
                                                    ClientRepository clientRepository,
                                                    ProductRepository productRepository,
                                                    CategoryRepository categoryRepository) {
        return new DeveloperDataInitializer(administratorRepository, clientRepository, categoryRepository, productRepository);
    }

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
    public BasketItemService basketItemService(BasketItemRepository basketItemRepository, ClientService clientService,
                                               ProductRepository productRepository, SalesService salesService) {
        return new BasketItemServiceImpl(basketItemRepository, clientService, productRepository, salesService);
    }

    @Bean
    public DebugService debugService(AdministratorRepository administratorRepository, ClientRepository clientRepository,
                                     ProductRepository productRepository, CategoryRepository categoryRepository,
                                     SessionRepository sessionRepository, BasketItemRepository basketItemRepository) {
        return new DebugServiceImpl(administratorRepository, clientRepository,
                productRepository, categoryRepository,
                sessionRepository, basketItemRepository);
    }

    @Bean
    public SalesService salesService(SalesRepository salesRepository) {
        return new SalesServiceImpl(salesRepository);
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
