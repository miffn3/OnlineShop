package net.thumbtack.onlineshop.entity;

import java.util.List;

public class Cart {

    private long id;
    private long clientId;
    private List<Product> products;
    private int count;
}
