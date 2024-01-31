package com.auction.model;


import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;
    private String productName;
    @Column(columnDefinition = "VARCHAR2(255) DEFAULT 'token'")
    private String seller;
    private double minimumBid;
}
