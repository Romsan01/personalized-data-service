package com.nielseniq.personalized.data.service.kafka.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private String productId;
    private String category;
    private String brand;
}
