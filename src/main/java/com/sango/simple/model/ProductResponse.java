package com.sango.simple.model;

import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {
    private List<Product> products;
}
