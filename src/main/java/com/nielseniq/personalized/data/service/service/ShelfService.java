package com.nielseniq.personalized.data.service.service;

import com.nielseniq.personalized.data.service.repository.model.ProductDTO;
import com.nielseniq.personalized.data.service.repository.model.ShelfDTO;
import com.nielseniq.personalized.data.service.repository.model.ShopperDTO;

public interface ShelfService {
    ShelfDTO saveShelf(ShopperDTO shopper, ProductDTO product, Double relevancyScore);
}
