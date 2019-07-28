package net.thumbtack.onlineshop.dto;

import lombok.Data;

@Data
public class BasketItemDto {

    private Long id;

    private String name;

    private Long price;

    private Long count;
}
