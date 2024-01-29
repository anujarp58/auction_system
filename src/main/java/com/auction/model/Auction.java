package com.auction.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Auction {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long auctionId;
        private long productId;
        private boolean ended;
        private String winner;
        private Double winnersBidAmount;

}
