package com.nielseniq.personalized.data.service.web;

import com.nielseniq.personalized.data.service.kafka.model.Product;
import com.nielseniq.personalized.data.service.kafka.model.ShopperList;
import com.nielseniq.personalized.data.service.service.KafkaProducerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KafkaProducerController {

    private final KafkaProducerService producerService;

    public KafkaProducerController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/api/produce/Product/")
    public String produce( @RequestBody List<Product> message) {
        producerService.sendProductMessage("Product", message);
        return "Message sent to Kafka Topic! Topic: Product  " + "Message: " + message + "\n";
    }

    @PostMapping("/api/produce/Shelf/")
    public String produceShelf( @RequestBody List<ShopperList> message) {
        producerService.sendShelfMessage("Shelf", message);
        return "Message sent to Kafka Topic! Topic: Shelf  " + "Message: " + message + "\n";
    }

}
