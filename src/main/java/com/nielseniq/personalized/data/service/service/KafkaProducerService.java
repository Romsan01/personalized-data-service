package com.nielseniq.personalized.data.service.service;

import com.nielseniq.personalized.data.service.kafka.model.Product;
import com.nielseniq.personalized.data.service.kafka.model.ShopperList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, List<Product>> productKafkaTemplate;

    @Autowired
    private KafkaTemplate<String, List<ShopperList>> shelfKafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendProductMessage(String topic, List<Product> message) {
        productKafkaTemplate.send(topic, message);
    }

    public void sendShelfMessage(String topic, List<ShopperList> message) {
        shelfKafkaTemplate.send(topic, message);
    }
}
