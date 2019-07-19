package net.thumbtack.onlineshop.service.impl;

import net.thumbtack.onlineshop.entity.Product;
import net.thumbtack.onlineshop.repository.iface.ProductRepository;
import net.thumbtack.onlineshop.service.iface.CategoryService;
import net.thumbtack.onlineshop.service.iface.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

public class ProductServiceTest {
    private ProductService underTest;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryService categoryService;


    @Captor
    public ArgumentCaptor<Product> captor;

    @Captor
    public ArgumentCaptor<Integer> captorInteger;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new ProductServiceImpl(productRepository,categoryService);
    }


    @Test
    public void addProduct() {
        Product product = new Product(1,"tv",100,100,null);

        underTest.addProduct(product);
        verify(productRepository).addProduct(captor.capture());
        Product value = captor.getValue();
        assertEquals(product,value);
    }

    @Test
    public void updateProduct() {
        Product product = new Product(1,"tv",100,100,null);

        underTest.updateProduct(product);
        verify(productRepository).updateProduct(captor.capture());
        Product value = captor.getValue();
        assertEquals(product,value);
    }

    @Test
    public void deleteProduct() {
        Product product = new Product(1,"tv",100,100,null);

        underTest.deleteProduct(product.getId());
        verify(productRepository).deleteProduct(captorInteger.capture());
        Integer value = captorInteger.getValue();
        assertEquals((Integer)product.getId(),value);
    }

    @Test
    public void getProduct() {
        Product product = new Product(1,"tv",100,100,null);

        underTest.getProduct(product.getId());
        verify(productRepository).getProductById(captorInteger.capture());
        Integer value = captorInteger.getValue();
        assertEquals((Integer)product.getId(),value);
    }

}