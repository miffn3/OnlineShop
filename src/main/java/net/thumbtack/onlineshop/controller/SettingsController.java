package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.dto.response.SettingsDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/settings")
public class SettingsController {
    @Value("${max_name_length}")
    private Integer maxLength;

    @Value("${min_password_length}")
    private Integer minLength;

    @GetMapping("/")
    public ResponseEntity<SettingsDto> getSettings(
            @CookieValue(value = "JAVASESSIONID", defaultValue="none") String cookie) {

        SettingsDto settings = new SettingsDto();
        settings.setMaxNameLength(maxLength);
        settings.setMinPasswordLength(minLength);

        return new ResponseEntity<>(settings, HttpStatus.OK);
    }

}
