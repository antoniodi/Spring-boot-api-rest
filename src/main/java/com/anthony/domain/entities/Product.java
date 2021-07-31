package com.anthony.domain.entities;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.*;

@Entity
public class Product {

    @Id @GeneratedValue
    private Long id;
    private String name;
    private Double price;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    public Product( Long id, String name, Double price, Category category ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public Product() {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @ResponseStatus( HttpStatus.NOT_FOUND )
    public static class ProductNotFoundException extends RuntimeException {

        public ProductNotFoundException( Long id ) {
            super( "Product with id: " + id + ", not found." );
        }

    }
}
