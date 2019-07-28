package net.thumbtack.onlineshop.service.iface;

import net.thumbtack.onlineshop.dto.BasketItemDto;
import net.thumbtack.onlineshop.entity.BasketItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BasketItemService {
    BasketItem addItem(BasketItemDto itemDto, Long id);
    BasketItem buyItem(BasketItemDto itemDto, Long id);
    void removeItem(Long productId, Long userId);
    void changeCount(BasketItemDto item, Long id);
    List<BasketItem> buyInBasket(List<BasketItemDto> itemDtoList, Long id);
}
