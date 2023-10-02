package com.nielseniq.personalized.data.service.repository.model;

import lombok.Data;

@Data
public class ShelfDTO {
    private Long id;
    private ShopperDTO shopper;
    private ProductDTO product;
    private Double relevancyScore;
}