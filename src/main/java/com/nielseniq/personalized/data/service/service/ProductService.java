package com.nielseniq.personalized.data.service.service;

import com.nielseniq.personalized.data.service.repository.model.ProductDTO;

import java.util.Optional;

public interface ProductService {
    ProductDTO saveProduct(String productId, String category, String brand);

    Optional<ProductDTO> findByProductId(String productId);
}
