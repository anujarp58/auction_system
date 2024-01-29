package com.auction.controller;

import com.auction.model.Bid;
import com.auction.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bid")
public class BidController {
    @Autowired
    private BidService bidService;
    @PostMapping("/{productId}")
    public ResponseEntity<Bid> placeBid(String token,
                                        @PathVariable long productId,
                                        @RequestParam long bidAmount) {

        Bid bid = bidService.placeBid(token, productId, bidAmount);
        return ResponseEntity.status(HttpStatus.CREATED).body(bid);
    }

 }
