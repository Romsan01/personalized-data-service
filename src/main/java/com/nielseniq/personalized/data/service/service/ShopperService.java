package com.nielseniq.personalized.data.service.service;

import com.nielseniq.personalized.data.service.repository.model.ShopperDTO;
import java.util.Optional;

public interface ShopperService {
    ShopperDTO saveShopper(String shopperId);

    Optional<ShopperDTO> findByShopperId(String shopperId);
}
