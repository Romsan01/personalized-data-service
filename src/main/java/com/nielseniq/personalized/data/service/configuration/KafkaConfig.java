package com.nielseniq.personalized.data.service.configuration;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nielseniq.personalized.data.service.kafka.model.Product;
import com.nielseniq.personalized.data.service.kafka.model.ShopperList;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${shelf.topic.name}")
    private String shelfTopicName;

    @Value(value = "${product.topic.name}")
    private String productTopicName;

    public ConsumerFactory<String, List<ShopperList>> shelfConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, shelfTopicName);
        ObjectMapper om = new ObjectMapper();
        JavaType type = om.getTypeFactory().constructCollectionType(List.class, ShopperList.class);

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<List<ShopperList>>(type,om,false));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, List<ShopperList>> shelfKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, List<ShopperList>> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(shelfConsumerFactory());
        return factory;
    }

    public ConsumerFactory<String, List<Product>> productConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, productTopicName);
        ObjectMapper om = new ObjectMapper();
        JavaType type = om.getTypeFactory().constructCollectionType(List.class, Product.class);

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<List<Product>>(type,om,false));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, List<Product>> productKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, List<Product>> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(productConsumerFactory());
        return factory;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, "20971520");

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ProducerFactory<String, List<Product>> productProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, List<Product>> productKafkaTemplate() {
        return new KafkaTemplate<>(productProducerFactory());
    }

    @Bean
    public ProducerFactory<String, List<ShopperList>> shelfProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, List<ShopperList>> shelfKafkaTemplate() {
        return new KafkaTemplate<>(shelfProducerFactory());
    }
}
