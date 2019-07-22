package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.dto.request.ProductDto;
import net.thumbtack.onlineshop.entity.Product;
import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.exception.SessionDoesntExistException;
import net.thumbtack.onlineshop.service.iface.ProductService;
import net.thumbtack.onlineshop.service.iface.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {
    private final ProductService productService;
    private final SessionService sessionService;

    public ProductController(ProductService productService, SessionService sessionService) {
        this.productService = productService;
        this.sessionService = sessionService;
    }

    @PostMapping("/")
    public ResponseEntity<Product> addProduct(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie,
            @RequestBody @Valid ProductDto productDto) {

        Session session = sessionService.getSession(cookie);
        if (session == null) {
            throw new SessionDoesntExistException();
        }

        Product product = productService.addProduct(productDto);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie,
            @PathVariable Long id) {

        Session session = sessionService.getSession(cookie);
        if (session == null) {
            throw new SessionDoesntExistException();
        }

        Product product = this.productService.getProduct(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> editProduct(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie,
            @PathVariable Long id,
            @RequestBody ProductDto productDto) {

        Session session = sessionService.getSession(cookie);
        if (session == null) {
            throw new SessionDoesntExistException();
        }

        Product product = productService.updateProduct(productDto, id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie,
            @PathVariable Long id) {

        Session session = sessionService.getSession(cookie);
        if (session == null) {
            throw new SessionDoesntExistException();
        }

        productService.deleteProduct(id);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie) {

        Session session = sessionService.getSession(cookie);
        if (session == null) {
            throw new SessionDoesntExistException();
        }

        List<Product> allProducts = this.productService.getAllProducts();
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }
}
