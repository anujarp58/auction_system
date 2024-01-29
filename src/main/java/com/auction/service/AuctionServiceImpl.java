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

        Product product = productRepository.findById(productId).get();
        List<Bid> allBids = bidRepository.findAllBidsByProductId(productId);
        //check if auction has ended
        // if both are same check the timestamp
        List<Bid> winningBid = determineWinningBids(allBids, product.getMinimumBid());
        Auction auction = new Auction();
        auction.setProductId(winningBid.get(0).getProductId());
        auction.setBidId(winningBid.get(0).getBidId());
        auction.setWinnersBidAmount(winningBid.get(0).getAmount());
        auction.setWinner(winningBid.get(0).getBuyer());
        auction.setEnded(true);
        auctionRepository.save(auction);
        return auction;
    }
    /*
    private Bid determineWinningBid(List<Bid> allBids, double minimumBid) {
        if (!allBids.isEmpty()) {
            allBids.sort((bid1, bid2) -> Double.compare(bid2.getAmount(), bid1.getAmount()));
            return allBids.get(0);
        } else {
            return null;
        }
    }*/

    public List<Bid> determineWinningBids(List<Bid> allBids, double minimumBid) {
        List<Bid> winningBids = new ArrayList<>();

        if (!allBids.isEmpty()) {
            allBids.sort(Comparator.comparingDouble(Bid::getAmount).reversed()
                    .thenComparing(Bid::getTimestamp).reversed());
            double highestAmount = allBids.get(0).getAmount();

            List<Bid> highestBids = allBids.stream()
                    .filter(bid -> bid.getAmount() == highestAmount)
                    .collect(Collectors.toList());

            if (highestBids.size() >= 2) {
                highestBids.sort(Comparator.comparing(Bid::getTimestamp).reversed());
                winningBids.add(highestBids.get(0));
            } else {
                winningBids.add(allBids.get(0));
            }
        }
        return winningBids;
    }
}
