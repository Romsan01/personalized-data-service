package com.nielseniq.personalized.data.service.kafka.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Shelf {
    private String productId;
    private Double relevancyScore;
}
