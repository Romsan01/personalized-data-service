package com.nielseniq.personalized.data.service.repository.model;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String productId;
    private String category;
    private String brand;
}
