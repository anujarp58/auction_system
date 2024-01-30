package com.auction.service;

import com.auction.model.Auction;
import com.auction.model.Bid;
import com.auction.model.Product;
import com.auction.repository.AuctionRepository;
import com.auction.repository.BidRepository;
import com.auction.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
            throw new IllegalArgumentException("Product not found with ID: " + productId);
        }

        Product product = optionalProduct.get();
        List<Bid> allBids = bidRepository.findAllBidsByProductId(product.getProductId());
        Bid winningBid = determineWinningBid(allBids);
        Auction auction = new Auction();
        auction.setProductId(winningBid.getProductId());
        auction.setBidId(winningBid.getBidId());
        auction.setWinnersBidAmount(winningBid.getAmount());
        auction.setWinner(winningBid.getBuyer());
        auction.setEnded(true);
        auctionRepository.save(auction);
        return auction;
    }

    private Bid determineWinningBid(List<Bid> allBids) {
        if (!allBids.isEmpty()) {
            allBids.sort((bid1, bid2) -> Double.compare(bid2.getAmount(), bid1.getAmount()));
            return allBids.get(0);
        } else {
            return null;
        }
    }

    /*
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
    }*/
}
