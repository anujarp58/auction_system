package com.auction.service;

import com.auction.exception.ProductNotAvailable;
import com.auction.model.Product;
import com.auction.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Override
    public Product registerProduct(String token, Product product) {
        product.setSeller(token);
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAllProducts() {
        List<Product> productList =productRepository.findAll();
        if (productList.isEmpty()) {
            throw new ProductNotAvailable("No products found");
        }
        return productList;
    }
}
