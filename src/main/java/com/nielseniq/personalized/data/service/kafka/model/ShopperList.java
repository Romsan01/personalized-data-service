package com.nielseniq.personalized.data.service.kafka.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ShopperList {
    private String shopperId;
    private List<Shelf> shelf;
}
