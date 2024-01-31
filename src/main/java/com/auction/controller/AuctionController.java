package com.auction.controller;

import com.auction.model.Auction;
import com.auction.service.AuctionService;
import com.auction.service.TokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auction")
public class AuctionController {
    @Autowired
    private TokenValidator tokenValidator;
    @Autowired
    private AuctionService auctionService;

    @PostMapping("/{productId}/end")
    public ResponseEntity<Auction> endAuction(@RequestHeader("x-token") String token,
                                              @PathVariable long productId){
        if (!tokenValidator.isTokenValid(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Auction auction = auctionService.endAuction(productId);
        return  ResponseEntity.ok(auction);
    }
}
