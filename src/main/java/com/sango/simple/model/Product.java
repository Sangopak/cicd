package com.sango.simple.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    private String productId;
    private String productName;
    private String productCode;
    private String releaseDate;
    private String description;
    private BigDecimal price;
    private Double starRating;
    private String imageUrl;
}
