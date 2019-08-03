package net.thumbtack.onlineshop.repository.iface;

import net.thumbtack.onlineshop.entity.Sales;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SalesRepository extends CrudRepository<Sales, Long> {
    Page<Sales> findAll(Pageable pageable);

    Optional<Sales> findByUserIdAndAndProductId(Long userId, Long productId);
}
