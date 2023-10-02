package com.nielseniq.personalized.data.service.service.impl;

import com.nielseniq.personalized.data.service.kafka.model.Shelf;
import com.nielseniq.personalized.data.service.kafka.model.ShopperList;
import com.nielseniq.personalized.data.service.repository.model.ProductDTO;
import com.nielseniq.personalized.data.service.repository.model.ShopperDTO;
import com.nielseniq.personalized.data.service.service.ProductService;
import com.nielseniq.personalized.data.service.service.ShelfProcessorService;
import com.nielseniq.personalized.data.service.service.ShelfService;
import com.nielseniq.personalized.data.service.service.ShopperService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShelfProcessorServiceImpl implements ShelfProcessorService {

    private final ProductService productService;
    private final ShopperService shopperService;
    private final ShelfService shelfService;

    public ShelfProcessorServiceImpl(ProductService productService, ShopperService shopperService, ShelfService shelfService) {
        this.productService = productService;
        this.shopperService = shopperService;
        this.shelfService = shelfService;
    }
    @Override
    public void createShopperList(ShopperList shopperList) {

        Optional<ShopperDTO> shopperDTO = shopperService.findByShopperId(shopperList.getShopperId());

        ShopperDTO shopper;
        if (!shopperDTO.isPresent()) {
            shopper = shopperService.saveShopper(shopperList.getShopperId());
        } else {
            shopper = shopperDTO.get();
        }

        if(shopperList.getShelf() == null || shopperList.getShelf().isEmpty()) {
            System.out.println("No shelf items found for shopperDTO: " + shopperDTO.get().getShopperId());
            return;
        }
        for(Shelf shelf : shopperList.getShelf()) {

            Optional<ProductDTO> productDTO = productService.findByProductId(shelf.getProductId());
            ProductDTO product;
            if (!productDTO.isPresent()) {
                product = productService.saveProduct(shelf.getProductId(), "", "");
            } else {
                product = productDTO.get();
            }

            try {
                shelfService.saveShelf(shopper, product, shelf.getRelevancyScore());
            }
            catch(Exception e) {
                System.out.println("Unable to save the record due to Exception: " + e.getMessage());

            }
        }
    }
}
