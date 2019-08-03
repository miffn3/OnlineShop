package net.thumbtack.onlineshop.service.impl;

import net.thumbtack.onlineshop.dto.BasketItemDto;
import net.thumbtack.onlineshop.entity.BasketItem;
import net.thumbtack.onlineshop.entity.Client;
import net.thumbtack.onlineshop.entity.Product;
import net.thumbtack.onlineshop.repository.iface.BasketItemRepository;
import net.thumbtack.onlineshop.repository.iface.ProductRepository;
import net.thumbtack.onlineshop.service.iface.BasketItemService;
import net.thumbtack.onlineshop.service.iface.ClientService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class BasketItemServiceImpl implements BasketItemService {
    private final BasketItemRepository basketItemRepository;
    private final ClientService clientService;
    private final ProductRepository productRepository;

    public BasketItemServiceImpl(BasketItemRepository basketItemRepository, ClientService clientService, ProductRepository productRepository) {
        this.basketItemRepository = basketItemRepository;
        this.clientService = clientService;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public BasketItem addItem(BasketItemDto itemDto, Long id) {
        BasketItem basketItem;

        if (itemDto.getCount() == null) {
            itemDto.setCount(1L);
        }

        if (basketItemRepository.findBasketItemByProductIdAndUserId(itemDto.getId(), id).isPresent()) {
            basketItem = basketItemRepository.findBasketItemByProductIdAndUserId(itemDto.getId(), id).get();
        } else {
            basketItem = new BasketItem();
            basketItem.setUserId(id);
            basketItem.setCount(itemDto.getCount());
            Product product = productRepository.findById(itemDto.getId()).get();
            basketItem.setProduct(product);
            return basketItemRepository.save(basketItem);
        }

        Long count = itemDto.getCount() + basketItem.getCount();
        basketItem.setCount(count);

        return basketItemRepository.save(basketItem);
    }

    @Override
    public BasketItem buyItem(BasketItemDto itemDto, Long id) {
        Product product = productRepository.findById(itemDto.getId()).get();
        Client client = clientService.getClientById(id);

        if (itemDto.getPrice() > client.getDeposit()) {
            return null;
        }

        if (product.getCount() <= 0) {
            return null;
        }

        product.setCount(product.getCount() - 1);
        client.setDeposit(client.getDeposit() - itemDto.getPrice());
        productRepository.save(product);
        clientService.getMoney(itemDto.getPrice(), id);

        BasketItem basketItem = new BasketItem();
        basketItem.setProduct(product);
        basketItem.setCount(0L);
        basketItem.setUserId(id);
        return basketItemRepository.save(basketItem);
    }

    @Override
    public void removeItem(Long productId, Long userId) {
        BasketItem basketItem = basketItemRepository.findBasketItemByProductIdAndUserId(productId, userId).get();
        basketItemRepository.delete(basketItem);
    }

    @Override
    public void changeCount(BasketItemDto item, Long id) {
        BasketItem basketItem = basketItemRepository.findBasketItemByProductIdAndUserId(item.getId(), id).get();
        basketItem.setCount(item.getCount());
        basketItemRepository.save(basketItem);
    }

    @Override
    public List<BasketItem> buyInBasket(List<BasketItemDto> itemDtoList, Long id) {
        List<BasketItem> bought = new ArrayList<>();
        for (BasketItemDto item:itemDtoList) {
            bought.add(buyItem(item, id));
        }
        return bought;
    }
}
