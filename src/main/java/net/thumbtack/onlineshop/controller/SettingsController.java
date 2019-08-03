package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.dto.response.SettingsDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/settings")
public class SettingsController {
    @Value("${max_name_length}")
    private Integer maxLength;

    @Value("${min_password_length}")
    private Integer minLength;

    @GetMapping("/")
    public ResponseEntity<SettingsDto> getSettings(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie) {

        SettingsDto settings = new SettingsDto();
        settings.setMaxNameLength(maxLength);
        settings.setMinPasswordLength(minLength);

        return new ResponseEntity<>(settings, HttpStatus.OK);
    }

}
