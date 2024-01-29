package com.auction.service;

import com.auction.model.Product;

public interface ProductService {
    Product registerProduct(String token, Product product);
}
