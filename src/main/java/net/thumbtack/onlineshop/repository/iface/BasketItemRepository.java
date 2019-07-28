package net.thumbtack.onlineshop.repository.iface;

import net.thumbtack.onlineshop.entity.BasketItem;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BasketItemRepository extends CrudRepository<BasketItem, Long> {
    Optional<BasketItem> findBasketItemByProductIdAndUserId(Long productId, Long userId);
}
