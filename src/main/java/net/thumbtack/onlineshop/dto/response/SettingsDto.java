package net.thumbtack.onlineshop.dto.response;

import lombok.Data;

@Data
public class SettingsDto {
    private Integer maxNameLength;
    private Integer minPasswordLength;
}
