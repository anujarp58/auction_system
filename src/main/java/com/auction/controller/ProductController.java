package com.auction.controller;

import com.auction.model.Product;
import com.auction.service.ProductService;
import com.auction.service.TokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sell")
public class ProductController {
    @Autowired
    private TokenValidator tokenValidator;
    @Autowired
    private ProductService productService;
    @PostMapping("/product")
    public ResponseEntity<Product> registerProduct(@RequestHeader("x-token") String token,
                                                   @RequestBody Product product){
        if (!tokenValidator.isTokenValid(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        product = productService.registerProduct(token, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);

    }
}
