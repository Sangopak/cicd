package com.sango.simple.controller;

import com.sango.simple.model.Product;
import com.sango.simple.model.ProductResponse;
import com.sango.simple.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(path = "/products", produces = "application/json")
    public List<Product> getAllProducts(){
        return productService.getAllProducts().getProducts();
    }
}
