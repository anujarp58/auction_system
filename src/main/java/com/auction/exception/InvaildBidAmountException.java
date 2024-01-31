package com.auction.exception;

public class InvaildBidAmountException extends RuntimeException {
    public InvaildBidAmountException(Exception e) {
        super(e);
    }
    public InvaildBidAmountException(String message) {
        super(message);
    }
}
