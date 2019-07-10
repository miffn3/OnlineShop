package net.thumbtack.onlineshop.dto.request;

public class CategoryCreateRequestDto {
    private String name;
    private int parentId;

    public CategoryCreateRequestDto(String name, int parentId) {
        this.name = name;
        this.parentId = parentId;
    }

    public CategoryCreateRequestDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
