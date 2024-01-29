package com.auction.service;

import com.auction.model.Product;
import com.auction.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Override
    public Product registerProduct(String token, Product product) {
        product.setSeller(token);
        return productRepository.save(product);
    }

}
