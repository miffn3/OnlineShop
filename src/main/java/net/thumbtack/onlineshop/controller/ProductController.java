package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.dto.request.ProductDto;
import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.entity.Product;
import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.exception.SessionAccessDeniedException;
import net.thumbtack.onlineshop.exception.SessionDoesntExistException;
import net.thumbtack.onlineshop.service.iface.AdministratorService;
import net.thumbtack.onlineshop.service.iface.ProductService;
import net.thumbtack.onlineshop.service.iface.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {
    private final ProductService productService;
    private final SessionService sessionService;
    private final AdministratorService administratorService;

    public ProductController(ProductService productService, SessionService sessionService,
                             AdministratorService administratorService) {
        this.productService = productService;
        this.sessionService = sessionService;
        this.administratorService = administratorService;
    }

    @PostMapping("/")
    public ResponseEntity<Product> addProduct(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie,
            @RequestBody @Valid ProductDto productDto) {

        checkSession(cookie);

        Product product = productService.addProduct(productDto);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie,
            @PathVariable Long id) {

        checkSession(cookie);

        Product product = this.productService.getProduct(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> editProduct(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie,
            @PathVariable Long id,
            @RequestBody ProductDto productDto) {

        checkSession(cookie);

        Product product = productService.updateProduct(productDto, id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie,
            @PathVariable Long id) {

        checkSession(cookie);

        productService.deleteProduct(id);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie) {

        checkSession(cookie);

        List<Product> allProducts = this.productService.getAllProducts();
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    private void checkSession(@CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie) {
        Session session = sessionService.getSession(cookie);
        if (session == null) {
            throw new SessionDoesntExistException();
        }

        Administrator administrator = administratorService.getAdministratorById(session.getUserId());
        if (administrator == null) {
            throw new SessionAccessDeniedException();
        }
    }
}
