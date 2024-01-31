package com.auction.controller;

import com.auction.model.Bid;
import com.auction.service.BidService;
import com.auction.service.TokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bid")
public class BidController {
    @Autowired
    private TokenValidator tokenValidator;
    @Autowired
    private BidService bidService;

    @PostMapping("/{productId}")
    public ResponseEntity<Bid> placeBid(@RequestHeader("x-token") String token,
                                        @PathVariable long productId,
                                        @RequestParam long bidAmount) {
        if (!tokenValidator.isTokenValid(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Bid bid = bidService.placeBid(token, productId, bidAmount);
        return ResponseEntity.status(HttpStatus.CREATED).body(bid);
    }

 }
