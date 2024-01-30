package com.auction.service;

import com.auction.model.Auction;
import com.auction.model.Bid;
import com.auction.model.Product;
import com.auction.repository.AuctionRepository;
import com.auction.repository.BidRepository;
import com.auction.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuctionServiceImpl implements AuctionService{
    @Autowired
    BidRepository bidRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    AuctionRepository auctionRepository;
    @Override
    public Auction endAuction(long productId) {

        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new IllegalArgumentException("Product not available for auction " + productId);
        }

        Product product = optionalProduct.get();

        List<Bid> allBids = bidRepository.findAllBidsByProductId(product.getProductId());
        Bid winningBid = determineWinningBid(allBids);
        Auction auction = new Auction();
        auction.setProductId(winningBid.getProductId());
        //auction.setBidId(winningBid.getBidId());
        auction.setWinnersBidAmount(winningBid.getAmount());
        auction.setWinner(winningBid.getBidId());
        auction.setEnded(true);
        auctionRepository.save(auction);
        return auction;
    }
    /*
    private Bid determineWinningBid(List<Bid> allBids) {
        if (!allBids.isEmpty()) {
            allBids.sort((bid1, bid2) -> Double.compare(bid2.getAmount(), bid1.getAmount()));
            return allBids.get(0);
        } else {
            return null;
        }
    }*/


    public Bid determineWinningBid(List<Bid> allBids) {
        List<Bid> sameAmountBids = new ArrayList<>();

        if (!allBids.isEmpty()) {
            allBids.sort(Comparator.comparingDouble(Bid::getAmount).reversed());
            double highestAmount = allBids.get(0).getAmount();

           sameAmountBids = allBids.stream()
                    .filter(bid -> bid.getAmount() == highestAmount)
                    .collect(Collectors.toList());

           if (sameAmountBids.size() >= 2) {
                sameAmountBids.sort(Comparator.comparing(Bid::getTimestamp));
                sameAmountBids.get(0);
           } else {
                sameAmountBids.get(0);
           }
        }
        return  sameAmountBids.get(0);
    }
}
