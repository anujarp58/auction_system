package com.auction.service;

import com.auction.model.Product;

import java.util.List;

public interface ProductService {
    Product registerProduct(String token, Product product);
    List<Product> findAllProducts();
}
