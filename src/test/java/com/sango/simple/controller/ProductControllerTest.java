package com.sango.simple.controller;

import com.sango.simple.model.ProductResponse;
import com.sango.simple.service.ProductService;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private ProductResponse productResponse;
    private ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private ProductService productService;

    @Before
    public void setUp() throws Exception {
        productResponse = objectMapper.readValue((new ClassPathResource("products-mock.json").getFile()),ProductResponse.class);
    }

    @Test
    public void getAllProducts() throws Exception{
        Mockito.when(productService.getAllProducts()).thenReturn(productResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("http://localhost:8081/api/products")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }
}