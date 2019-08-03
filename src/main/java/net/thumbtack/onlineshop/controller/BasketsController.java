package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.dto.BasketItemDto;
import net.thumbtack.onlineshop.entity.BasketItem;
import net.thumbtack.onlineshop.entity.Client;
import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.exception.SessionAccessDeniedException;
import net.thumbtack.onlineshop.exception.SessionDoesntExistException;
import net.thumbtack.onlineshop.service.iface.BasketItemService;
import net.thumbtack.onlineshop.service.iface.ClientService;
import net.thumbtack.onlineshop.service.iface.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/baskets")
public class BasketsController {
    private SessionService sessionService;
    private ClientService clientService;
    private BasketItemService basketItemService;

    public BasketsController(SessionService sessionService, ClientService clientService,
                             BasketItemService basketItemService) {
        this.sessionService = sessionService;
        this.clientService = clientService;
        this.basketItemService = basketItemService;
    }

    @PostMapping("/")
    public ResponseEntity<List<BasketItemDto>> addBasketItem(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie,
            @RequestBody BasketItemDto item) {
        Client client = getClient(cookie);
        List<BasketItemDto> basketItems = new ArrayList<>();
        basketItemService.addItem(item, client.getId());

        List<BasketItem> list = client.getProducts();
        for (BasketItem basketItem : list) {
            BasketItemDto basketItemDto = new BasketItemDto();
            basketItemDto.setCount(basketItem.getCount());
            basketItemDto.setId(basketItem.getProduct().getId());
            basketItemDto.setName(basketItem.getProduct().getName());
            basketItemDto.setPrice(basketItem.getProduct().getPrice());
            basketItems.add(basketItemDto);
        }
        return new ResponseEntity<>(basketItems, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeItem(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie,
            @PathVariable Long id) {
        Client client = getClient(cookie);
        basketItemService.removeItem(id, client.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<List<BasketItemDto>> changeCountOfItem(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie,
            @RequestBody BasketItemDto item) {
        Client client = getClient(cookie);
        basketItemService.changeCount(item, client.getId());
        List<BasketItemDto> basketItems = new ArrayList<>();
        List<BasketItem> list = client.getProducts();
        for (BasketItem basketItem : list) {
            BasketItemDto basketItemDto = new BasketItemDto();
            basketItemDto.setCount(basketItem.getCount());
            basketItemDto.setId(basketItem.getProduct().getId());
            basketItemDto.setName(basketItem.getProduct().getName());
            basketItemDto.setPrice(basketItem.getProduct().getPrice());
            basketItems.add(basketItemDto);
        }
        return new ResponseEntity<>(basketItems, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<BasketItemDto>> getBasketItem(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie) {
        Client client = getClient(cookie);
        List<BasketItemDto> basketItems = new ArrayList<>();
        List<BasketItem> list = client.getProducts();
        for (BasketItem basketItem : list) {
            BasketItemDto basketItemDto = new BasketItemDto();
            basketItemDto.setCount(basketItem.getCount());
            basketItemDto.setId(basketItem.getProduct().getId());
            basketItemDto.setName(basketItem.getProduct().getName());
            basketItemDto.setPrice(basketItem.getProduct().getPrice());
            basketItems.add(basketItemDto);
        }
        return new ResponseEntity<>(basketItems, HttpStatus.OK);
    }

    private Client getClient(@CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie) {
        Session session = sessionService.getSession(cookie);
        if (session == null) {
            throw new SessionDoesntExistException();
        }
        Client client = clientService.getClientById(session.getUserId());

        if (client == null) {
            throw new SessionAccessDeniedException();
        }
        return client;
    }
}
