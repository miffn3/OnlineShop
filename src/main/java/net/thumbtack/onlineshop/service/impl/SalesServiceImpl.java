package net.thumbtack.onlineshop.service.impl;

import net.thumbtack.onlineshop.entity.Sales;
import net.thumbtack.onlineshop.repository.iface.SalesRepository;
import net.thumbtack.onlineshop.service.iface.SalesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class SalesServiceImpl implements SalesService {
    private final SalesRepository salesRepository;

    public SalesServiceImpl(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    @Override
    public Page<Sales> findAll(Pageable pageable) {
        return salesRepository.findAll(pageable);
    }

    @Override
    public Sales addSales(Sales sales) {
        return salesRepository.save(sales);
    }

    @Override
    public Sales getSalesByUserIdAndProductId(Long userId, Long productId) {
        return salesRepository.findByUserIdAndAndProductId(userId, productId).orElse(null);
    }
}
