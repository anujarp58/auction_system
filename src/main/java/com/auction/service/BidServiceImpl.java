package com.auction.service;

import com.auction.model.Bid;
import com.auction.model.Product;
import com.auction.repository.BidRepository;
import com.auction.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BidServiceImpl implements BidService {
    @Autowired
    BidRepository bidRepository;
    @Autowired
    ProductRepository productRepository;
    @Override
    public Bid placeBid(String token, long productId, long amount) {

        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent() && amount < product.get().getMinimumBid()) {
            throw new IllegalArgumentException("Bid amount is less than the minimum bid");
        }
        Bid bid = new Bid();
        bid.setBuyer(token);
        bid.setAmount(amount);
        bid.setProductId(productId);
        bidRepository.save(bid);
        return bid;
    }
}
