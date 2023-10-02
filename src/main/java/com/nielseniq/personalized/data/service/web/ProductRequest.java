package com.nielseniq.personalized.data.service.web;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    private String shopperId;
    private String category;
    private String brand;
    private Integer limit;
}
