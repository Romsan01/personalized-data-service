package com.nielseniq.personalized.data.service.kafka.consumer;

import com.nielseniq.personalized.data.service.kafka.model.Product;
import com.nielseniq.personalized.data.service.kafka.model.ShopperList;
import com.nielseniq.personalized.data.service.service.ProductService;
import com.nielseniq.personalized.data.service.service.ShelfProcessorService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaConsumerListener {

    private final ShelfProcessorService shelfProcessorService;
    private final ProductService productService;

    public KafkaConsumerListener(ShelfProcessorService shelfProcessorService, ProductService productService) {
        this.shelfProcessorService = shelfProcessorService;
        this.productService = productService;
    }

    @Retryable(maxAttempts = 0)
    @KafkaListener(topics = "${shelf.topic.name}", containerFactory = "shelfKafkaListenerContainerFactory")
    public void shelfListener(List<ShopperList> shopperList) {
        if(shopperList != null && !shopperList.isEmpty()) {
            shopperList.forEach(temp -> {
                shelfProcessorService.createShopperList(temp);
            });
        }

    }

    @KafkaListener(topics = "${product.topic.name}", containerFactory = "productKafkaListenerContainerFactory")
    public void productListener(List<Product> products) {
        products.forEach(product -> {
            productService.saveProduct(product.getProductId(), product.getCategory(), product.getBrand());
        });
    }

}
