package net.thumbtack.onlineshop.entity;

import java.util.List;

public class Product {

    private String id;
    private String name;
    private long price;
    private long count;
    private List<Category> categories;

    public Product() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", categories=" + categories +
                '}';
    }
}
