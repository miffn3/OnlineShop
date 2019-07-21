package net.thumbtack.onlineshop.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class ProductDto {

    @Size(max = 50)
    private String name;

    private Long price;

    private Long count;

    List<Long> categories;
}
