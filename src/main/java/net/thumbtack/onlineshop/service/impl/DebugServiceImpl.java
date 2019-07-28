package net.thumbtack.onlineshop.service.impl;

import net.thumbtack.onlineshop.repository.iface.AdministratorRepository;
import net.thumbtack.onlineshop.repository.iface.BasketItemRepository;
import net.thumbtack.onlineshop.repository.iface.CategoryRepository;
import net.thumbtack.onlineshop.repository.iface.ClientRepository;
import net.thumbtack.onlineshop.repository.iface.ProductRepository;
import net.thumbtack.onlineshop.repository.iface.SessionRepository;
import net.thumbtack.onlineshop.service.iface.DebugService;

public class DebugServiceImpl implements DebugService {
    private final AdministratorRepository administratorRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SessionRepository sessionRepository;
    private final BasketItemRepository basketItemRepository;

    public DebugServiceImpl(AdministratorRepository administratorRepository, ClientRepository clientRepository,
                            ProductRepository productRepository, CategoryRepository categoryRepository,
                            SessionRepository sessionRepository, BasketItemRepository basketItemRepository) {
        this.administratorRepository = administratorRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.sessionRepository = sessionRepository;
        this.basketItemRepository = basketItemRepository;
    }

    @Override
    public boolean clearAll() {
        administratorRepository.deleteAll();
        clientRepository.deleteAll();
        basketItemRepository.deleteAll();
        productRepository.deleteAll();
        categoryRepository.deleteAll();
        sessionRepository.deleteAll();
        return true;
    }
}
