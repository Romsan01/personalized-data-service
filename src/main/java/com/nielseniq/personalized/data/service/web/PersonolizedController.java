package com.nielseniq.personalized.data.service.web;

import com.nielseniq.personalized.data.service.repository.model.ProductDTO;
import com.nielseniq.personalized.data.service.repository.model.ShopperDTO;
import com.nielseniq.personalized.data.service.service.PersonalizedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonolizedController {

    @Autowired
    private PersonalizedService personalizedService;

    @GetMapping("/api/products/byShopper/{shopperId}")
    public List<ProductDTO> getProductsByShopperIdCategoryAndBrand(
            @PathVariable String shopperId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(defaultValue = "10") int limit) {

        return personalizedService.findProductsByShopperIdCategoryAndBrand(shopperId, category, brand, Math.min(limit, 100));
    }

    @GetMapping("/api/shoppers/byProduct/{productId}")
    public List<ShopperDTO> getShoppersByProductId(
            @PathVariable String productId,
            @RequestParam(defaultValue = "10") int limit) {
        return personalizedService.findShoppersByProductId(productId, Math.min(limit, 1000));
    }
}
