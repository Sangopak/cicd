package com.sango.simple.service;

import com.sango.simple.model.ProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ProductResponse getAllProducts() {
        try {
            ProductResponse productResponse = objectMapper.readValue((new ClassPathResource("products-mock.json").getFile()),ProductResponse.class);
            log.info("Data from the service layer: {} ",productResponse);
            return productResponse;
        } catch (IOException e) {
            log.error("Error occurred: {}",e.getMessage());
            e.printStackTrace();
        }
        return new ProductResponse();
    }
}
