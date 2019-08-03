package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.dto.BasketItemDto;
import net.thumbtack.onlineshop.dto.response.PurchaseBasketResponseDto;
import net.thumbtack.onlineshop.entity.BasketItem;
import net.thumbtack.onlineshop.entity.Category;
import net.thumbtack.onlineshop.entity.Client;
import net.thumbtack.onlineshop.entity.Sales;
import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.exception.BuyingException;
import net.thumbtack.onlineshop.exception.SessionAccessDeniedException;
import net.thumbtack.onlineshop.exception.SessionDoesntExistException;
import net.thumbtack.onlineshop.service.iface.BasketItemService;
import net.thumbtack.onlineshop.service.iface.ClientService;
import net.thumbtack.onlineshop.service.iface.SalesService;
import net.thumbtack.onlineshop.service.iface.SessionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/purchases")
public class PurchasesController {
    private SessionService sessionService;
    private ClientService clientService;
    private BasketItemService basketItemService;
    private SalesService salesService;

    public PurchasesController(SessionService sessionService, ClientService clientService,
                               BasketItemService basketItemService, SalesService salesService) {
        this.sessionService = sessionService;
        this.clientService = clientService;
        this.basketItemService = basketItemService;
        this.salesService = salesService;
    }

    @PostMapping("/")
    public ResponseEntity<BasketItemDto> buy(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie,
            @RequestBody BasketItemDto item) {
        Client client = getClient(cookie);
        BasketItem basketItem = basketItemService.buyItem(item, client.getId());
        if (basketItem == null) {
            throw new BuyingException();
        }
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping("/baskets")
    public ResponseEntity<PurchaseBasketResponseDto> buyBasket(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie,
            @RequestBody List<BasketItemDto> items) {
        Client client = getClient(cookie);
        List<BasketItem> itemList = basketItemService.buyInBasket(items, client.getId());
        List<BasketItemDto> bought = new ArrayList<>();

        for (BasketItem basketItem : itemList) {
            BasketItemDto basketItemDto = new BasketItemDto();
            basketItemDto.setCount(basketItem.getCount());
            basketItemDto.setId(basketItem.getProduct().getId());
            basketItemDto.setName(basketItem.getProduct().getName());
            basketItemDto.setPrice(basketItem.getProduct().getPrice());
            bought.add(basketItemDto);
        }

        List<BasketItem> list = client.getProducts();
        List<BasketItemDto> remaining = new ArrayList<>();
        for (BasketItem basketItem : list) {
            BasketItemDto basketItemDto = new BasketItemDto();
            basketItemDto.setCount(basketItem.getCount());
            basketItemDto.setId(basketItem.getProduct().getId());
            basketItemDto.setName(basketItem.getProduct().getName());
            basketItemDto.setPrice(basketItem.getProduct().getPrice());
            remaining.add(basketItemDto);
        }
        PurchaseBasketResponseDto listOfProducts = new PurchaseBasketResponseDto();
        listOfProducts.setBought(bought);
        listOfProducts.setRemaining(remaining);
        return new ResponseEntity<>(listOfProducts, HttpStatus.OK);
    }

    @GetMapping("/")
    public Page<Sales> listSales(
            @RequestParam(name = "category", required = false) List<Category> categories,
            @NotNull final Pageable pageable) {
        return salesService.findAll(pageable);
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
