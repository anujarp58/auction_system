package com.auction.model;

import jakarta.persistence.*;
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
    private LocalDateTime timestamp;
    @PrePersist
    public void prePersist() {
        this.timestamp = LocalDateTime.now();
    }
}
