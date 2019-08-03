package net.thumbtack.onlineshop.dto.response;

import lombok.Data;
import net.thumbtack.onlineshop.dto.BasketItemDto;

import java.util.List;

@Data
public class PurchaseBasketResponseDto {

    private List<BasketItemDto> bought;

    private List<BasketItemDto> remaining;
}
