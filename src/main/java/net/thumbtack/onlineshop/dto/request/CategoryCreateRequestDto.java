package net.thumbtack.onlineshop.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@NotNull
public class CategoryCreateRequestDto {
    private String name;
    private Long parentId;
}
