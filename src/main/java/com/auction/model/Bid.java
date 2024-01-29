package com.auction.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bidId;
    private long productId;
    private String buyer;
    private long amount;
    @Setter(AccessLevel.NONE)
    private LocalDateTime timestamp;
    public void setTimestamp() {
        this.timestamp = LocalDateTime.now();
    }
}
