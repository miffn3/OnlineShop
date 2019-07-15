package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.entity.Product;
import net.thumbtack.onlineshop.service.iface.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/")
    public ResponseEntity<Product> addProduct(@CookieValue("JAVASESSIONID") String cookie, @RequestBody Product product) {
        this.productService.addProduct(product);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@CookieValue("JAVASESSIONID") String cookie, @PathVariable int id) {
        Product product = this.productService.getProduct(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> editProduct(@CookieValue("JAVASESSIONID") String cookie, @PathVariable int id,
                                               @RequestBody Product product) {
        Product productEdit = this.productService.getProduct(id);
        productEdit.setCategories(product.getCategories());
        productEdit.setName(product.getName());
        productEdit.setCount(product.getCount());
        productEdit.setPrice(product.getPrice());
        this.productService.updateProduct(productEdit);
        return new ResponseEntity<>(productEdit, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@CookieValue("JAVASESSIONID") String cookie,@PathVariable int id) {
        this.productService.deleteProduct(id);
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts(@CookieValue("JAVASESSIONID") String cookie) {
        List<Product> allProducts = this.productService.getAllProducts();
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }
}
