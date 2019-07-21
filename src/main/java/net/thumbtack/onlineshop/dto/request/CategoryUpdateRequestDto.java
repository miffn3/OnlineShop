package net.thumbtack.onlineshop.dto.request;

import lombok.Data;

@Data
public class CategoryUpdateRequestDto {
    private String name;
    private Long parentId;
}
