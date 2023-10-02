package com.nielseniq.personalized.data.service.service;

import com.nielseniq.personalized.data.service.kafka.model.ShopperList;

public interface ShelfProcessorService {
    void createShopperList(ShopperList shopperList);
}
