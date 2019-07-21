package net.thumbtack.onlineshop.service.iface;

import net.thumbtack.onlineshop.dto.request.ProductDto;
import net.thumbtack.onlineshop.entity.Category;
import net.thumbtack.onlineshop.entity.Product;
import net.thumbtack.onlineshop.repository.iface.ProductRepository;
import net.thumbtack.onlineshop.service.iface.CategoryService;
import net.thumbtack.onlineshop.service.iface.ProductService;
import net.thumbtack.onlineshop.service.impl.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductServiceTest {
    private ProductService underTest;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryService categoryService;


    @Captor
    public ArgumentCaptor<Product> captor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new ProductServiceImpl(productRepository,categoryService);
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

        underTest.addProduct(productDto);

        verify(productRepository).save(captor.capture());
        when(categoryService.getCategory(1L)).thenReturn(category);

        Product value = captor.getValue();
        assertEquals(product, value);

    }

    @Test
    public void updateProduct() {
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

        Optional<Product> opt = Optional.of(product);
        when(productRepository.findById(1L)).thenReturn(opt);

        underTest.updateProduct(updateRequestDto, product.getId());

        verify(productRepository).save(captor.capture());
        Product value = captor.getValue();
        assertEquals(updatedProduct, value);
    }

    @Test
    public void getProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setPrice(100L);
        product.setCount(100L);
        product.setName("Super TV 5000");

        Optional<Product> opt = Optional.of(product);

        when(productRepository.findById(1L)).thenReturn(opt);

        Product productTmp = underTest.getProduct(product.getId());
        assertEquals(productTmp, product);
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

        when(productRepository.findAll()).thenReturn(products);

        List<Product> productsTmp = underTest.getAllProducts();
        assertEquals(products, productsTmp);
    }
}