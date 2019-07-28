package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.service.iface.DebugService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/debug")
public class DebugController {
    private final DebugService debugService;

    public DebugController(DebugService debugService) {
        this.debugService = debugService;
    }

    @PostMapping("/clear")
    public ResponseEntity<Boolean> clearDataBase() {
        return ResponseEntity.ok(debugService.clearAll());
    }
}
