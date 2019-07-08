package net.thumbtack.onlineshop.entity;

import java.util.List;

public class Cart {

    private long id;
    private long clientId;
    private List<Product> products;
    private int count;

    public Cart() {
    }

    public Cart(long id, long clientId, List<Product> products, int count) {
        this.id = id;
        this.clientId = clientId;
        this.products = products;
        this.count = count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", products=" + products +
                ", count=" + count +
                '}';
    }
}
