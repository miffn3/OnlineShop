package net.thumbtack.onlineshop.service.iface;

import net.thumbtack.onlineshop.entity.Sales;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface SalesService {
    Page<Sales> findAll(Pageable pageable);

    Sales addSales(Sales sales);

    Sales getSalesByUserIdAndProductId(Long userId, Long productId);
}
