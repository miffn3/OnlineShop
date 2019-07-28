package net.thumbtack.onlineshop.util;

import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.entity.Category;
import net.thumbtack.onlineshop.entity.Client;
import net.thumbtack.onlineshop.entity.Product;
import net.thumbtack.onlineshop.repository.iface.AdministratorRepository;
import net.thumbtack.onlineshop.repository.iface.CategoryRepository;
import net.thumbtack.onlineshop.repository.iface.ClientRepository;
import net.thumbtack.onlineshop.repository.iface.ProductRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.util.HashSet;
import java.util.Set;

public class DeveloperDataInitializer implements ApplicationRunner {
    private final AdministratorRepository administratorRepository;
    private final ClientRepository clientRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public DeveloperDataInitializer(AdministratorRepository administratorRepository, ClientRepository clientRepository,
                                    CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.administratorRepository = administratorRepository;
        this.clientRepository = clientRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (administratorRepository.findByLogin("ivanich").isPresent()) {
            return;
        }

        Administrator administrator = new Administrator();
        administrator.setFirstName("Ivan");
        administrator.setLastName("Ivanov");
        administrator.setPatronymic("Ivanovich");
        administrator.setLogin("ivanich");
        administrator.setPassword("admin12345");
        administrator.setPosition("manager");
        administratorRepository.save(administrator);

        Client client = new Client();
        client.setFirstName("Petr");
        client.setLastName("Petrov");
        client.setPatronymic("Petrovich");
        client.setLogin("petrovich1'");
        client.setPassword("client12345");
        client.setEmail("petr12@mail.com");
        client.setAddress("ul.petrova");
        client.setPhone("554433");
        clientRepository.save(client);

        Category category = new Category();
        category.setName("tv");
        Category tmp = categoryRepository.save(category);

        Product product = new Product();
        product.setName("Super TV");
        product.setCount(100L);
        product.setPrice(10000L);
        Set<Category> categories = new HashSet<>();
        categories.add(tmp);
        product.setCategories(categories);
        productRepository.save(product);
    }
}
