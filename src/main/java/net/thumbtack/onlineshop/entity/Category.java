package net.thumbtack.onlineshop.entity;

public class Category {

    private long id;
    private String name;
    private long parentId;
    private String parentName;

    public Category() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
