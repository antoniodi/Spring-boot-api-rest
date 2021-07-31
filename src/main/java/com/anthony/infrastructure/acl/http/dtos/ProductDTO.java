package com.anthony.infrastructure.acl.http.dtos;

public class ProductDTO {

    private Long id;
    private String name;
    private String categoryName;

    public ProductDTO(Long id, String name, String categoryName) {
        this.id = id;
        this.name = name;
        this.categoryName = categoryName;
    }

    public ProductDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
