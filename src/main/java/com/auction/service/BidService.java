package com.auction.service;

import com.auction.model.Bid;

public interface BidService {
    Bid placeBid(String token, long productId, double amount);
}
