package com.auction.controller;

import com.auction.model.Auction;
import com.auction.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auctions")
public class AuctionController {
    @Autowired
    private AuctionService auctionService;
    @PostMapping("/{productId}/end")
    public ResponseEntity<Auction> endAuction(@PathVariable long productId){
        Auction auction = auctionService.endAuction(productId);
        return  ResponseEntity.ok(auction);
    }
}
