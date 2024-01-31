package com.auction.exception;

public class ProductNotAvailable extends RuntimeException {
    public ProductNotAvailable(Exception e) {
        super(e);
    }
    public ProductNotAvailable(String message) {
        super(message);
    }
}
