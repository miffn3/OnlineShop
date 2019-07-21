package net.thumbtack.onlineshop.service.iface;

import net.thumbtack.onlineshop.dto.request.ProductDto;
import net.thumbtack.onlineshop.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Product addProduct(ProductDto productDto);
    Product updateProduct(ProductDto productDto, Long id);
    void deleteProduct(Long id);
    Product getProduct(Long id);
    List<Product> getAllProducts();
}
