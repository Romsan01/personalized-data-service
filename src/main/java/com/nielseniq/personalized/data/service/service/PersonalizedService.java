package com.nielseniq.personalized.data.service.service;

import com.nielseniq.personalized.data.service.repository.model.ProductDTO;
import com.nielseniq.personalized.data.service.repository.model.ShopperDTO;

import java.util.List;

public interface PersonalizedService {
    List<ProductDTO> findProductsByShopperIdCategoryAndBrand(String shopperId, String category, String brand, int limit);
    List<ShopperDTO> findShoppersByProductId(String productId, int limit);
}
