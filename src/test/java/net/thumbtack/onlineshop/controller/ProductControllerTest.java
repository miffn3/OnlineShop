package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.dto.request.ProductDto;
import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.entity.Category;
import net.thumbtack.onlineshop.entity.Product;
import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.service.iface.AdministratorService;
import net.thumbtack.onlineshop.service.iface.ProductService;
import net.thumbtack.onlineshop.service.iface.SessionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ProductControllerTest {
    private ProductController underTest;

    @Mock
    private ProductService productService;

    @Mock
    private SessionService sessionService;

    @Mock
    private AdministratorService administratorService;


    @Before
    public void setUpClass() {
        MockitoAnnotations.initMocks(this);
        underTest = new ProductController(this.productService, this.sessionService, this.administratorService);
    }

    @Test
    public void addProduct() {
        Product product = new Product();
        product.setPrice(100L);
        product.setCount(100L);
        product.setName("Super TV 5000");

        Category category = new Category();
        category.setName("tv");
        category.setId(1L);
        Set<Category> categorySet = new HashSet<>();
        categorySet.add(category);
        product.setCategories(categorySet);

        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setCount(product.getCount());
        productDto.setPrice(product.getPrice());

        List<Long> listOfId = new ArrayList<>();
        listOfId.add(category.getId());
        productDto.setCategories(listOfId);

        Session session = new Session();
        session.setCookie("cookie");
        session.setUserId(1L);

        when(sessionService.getSession(session.getCookie())).thenReturn(session);
        when(productService.addProduct(productDto)).thenReturn(product);

        Product response = underTest.addProduct(session.getCookie(), productDto).getBody();
        assertEquals(product, response);
    }

    @Test
    public void getProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setPrice(100L);
        product.setCount(100L);
        product.setName("Super TV 5000");

        Session session = new Session();
        session.setCookie("cookie");
        session.setUserId(1L);

        when(sessionService.getSession(session.getCookie())).thenReturn(session);
        when(productService.getProduct(1L)).thenReturn(product);

        Product response = underTest.getProduct(session.getCookie(), 1L).getBody();
        assertEquals(product, response);
    }

    @Test
    public void editProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setPrice(100L);
        product.setCount(100L);
        product.setName("Super TV 5000");

        ProductDto updateRequestDto = new ProductDto();
        updateRequestDto.setPrice(50L);
        updateRequestDto.setCount(50L);
        updateRequestDto.setName("Not so good TV 100");

        Product updatedProduct = new Product();
        updatedProduct.setId(product.getId());
        updatedProduct.setPrice(updateRequestDto.getPrice());
        updatedProduct.setCount(updateRequestDto.getCount());
        updatedProduct.setName(updateRequestDto.getName());

        Session session = new Session();
        session.setCookie("cookie");
        session.setUserId(1L);

        when(sessionService.getSession(session.getCookie())).thenReturn(session);
        when(productService.updateProduct(updateRequestDto, 1L)).thenReturn(updatedProduct);

        Product response = underTest.editProduct(session.getCookie(), 1L, updateRequestDto).getBody();
        assertEquals(updatedProduct, response);
    }

    @Test
    public void getAllProducts() {
        Product product = new Product();
        product.setId(1L);
        product.setPrice(100L);
        product.setCount(100L);
        product.setName("Super TV 5000");

        List<Product> products = new ArrayList<>();
        products.add(product);

        Session session = new Session();
        session.setCookie("cookie");
        session.setUserId(1L);

        when(sessionService.getSession(session.getCookie())).thenReturn(session);
        when(productService.getAllProducts()).thenReturn(products);

        List<Product> response = underTest.getAllProducts(session.getCookie()).getBody();
        assertEquals(products, response);
    }
}