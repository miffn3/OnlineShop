package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.dto.BasketItemDto;
import net.thumbtack.onlineshop.dto.response.PurchaseBasketResponseDto;
import net.thumbtack.onlineshop.entity.BasketItem;
import net.thumbtack.onlineshop.entity.Client;
import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.exception.BuyingException;
import net.thumbtack.onlineshop.exception.SessionAccessDeniedException;
import net.thumbtack.onlineshop.exception.SessionDoesntExistException;
import net.thumbtack.onlineshop.service.iface.BasketItemService;
import net.thumbtack.onlineshop.service.iface.ClientService;
import net.thumbtack.onlineshop.service.iface.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/purchases")
public class PurchasesController {
    private SessionService sessionService;
    private ClientService clientService;
    private BasketItemService basketItemService;

    public PurchasesController(SessionService sessionService, ClientService clientService,
                               BasketItemService basketItemService) {
        this.sessionService = sessionService;
        this.clientService = clientService;
        this.basketItemService = basketItemService;
    }

    @PostMapping("/")
    public ResponseEntity<BasketItemDto> buy(
            @CookieValue(value = "JAVASESSIONID", defaultValue="none") String cookie,
            @RequestBody BasketItemDto item) {

        Client client = getClient(cookie);

        BasketItem basketItem = basketItemService.buyItem(item, client.getId());

        if(basketItem == null) {
            throw new BuyingException();
        }

        return new ResponseEntity<>(item, HttpStatus.OK);
    }



    @PostMapping("/baskets")
    public ResponseEntity<PurchaseBasketResponseDto> buyBasket(
            @CookieValue(value = "JAVASESSIONID", defaultValue="none") String cookie,
            @RequestBody List<BasketItemDto> items) {

        Client client = getClient(cookie);
        List<BasketItem> itemList = basketItemService.buyInBasket(items, client.getId());
        PurchaseBasketResponseDto listOfProducts = new PurchaseBasketResponseDto();
        return new ResponseEntity<>(listOfProducts, HttpStatus.OK);
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
