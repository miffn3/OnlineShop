package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.dto.request.ProductDto;
import net.thumbtack.onlineshop.entity.Product;
import net.thumbtack.onlineshop.service.iface.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/")
    public ResponseEntity<Product> addProduct(
            @RequestBody @Valid ProductDto productDto) {

        Product tmp = productService.addProduct(productDto);
        return new ResponseEntity<>(tmp, HttpStatus.OK);
    }


//    private final SessionService sessionService;
//
//    public ProductController(ProductService productService, SessionService sessionService) {
//        this.productService = productService;
//        this.sessionService = sessionService;
//    }
//
//    @PostMapping("/")
//    public ResponseEntity<Product> addProduct(
//            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie,
//            @RequestBody Product product) {
//
//        Session session;
//        try {
//            session = sessionService.getSession(cookie);
//        } catch (OnlineShopException ex) {
//            throw ex;
//        }
//
//        product.setId(productService.addProduct(product));
//        return new ResponseEntity<>(product,HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Product> getProduct(
//            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie,
//            @PathVariable int id) {
//
//        Session session;
//        try {
//            session = sessionService.getSession(cookie);
//        } catch (OnlineShopException ex) {
//            throw ex;
//        }
//
//        Product product = this.productService.getProduct(id);
//        return new ResponseEntity<>(product, HttpStatus.OK);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Product> editProduct(
//            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie,
//            @PathVariable int id,
//            @RequestBody Product product) {
//
//        Session session;
//        try {
//            session = sessionService.getSession(cookie);
//        } catch (OnlineShopException ex) {
//            throw ex;
//        }
//
//        Product productEdit = productService.getProduct(id);
//        productEdit.setCategories(product.getCategories());
//        productEdit.setName(product.getName());
//        productEdit.setCount(product.getCount());
//        productEdit.setPrice(product.getPrice());
//        productService.updateProduct(productEdit);
//        return new ResponseEntity<>(productEdit, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity deleteProduct(
//            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie,
//            @PathVariable int id) {
//
//        Session session;
//        try {
//            session = sessionService.getSession(cookie);
//        } catch (OnlineShopException ex) {
//            throw ex;
//        }
//
//        productService.deleteProduct(id);
//        return ResponseEntity.ok().body(null);
//    }
//
//    @GetMapping("/")
//    public ResponseEntity<List<Product>> getAllProducts(
//            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie) {
//
//        Session session;
//        try {
//            session = sessionService.getSession(cookie);
//        } catch (OnlineShopException ex) {
//            throw ex;
//        }
//
//        List<Product> allProducts = this.productService.getAllProducts();
//        return new ResponseEntity<>(allProducts, HttpStatus.OK);
//    }
}
