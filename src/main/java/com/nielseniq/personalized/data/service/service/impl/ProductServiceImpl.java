package com.nielseniq.personalized.data.service.service.impl;

import com.nielseniq.personalized.data.service.repository.ProductRepository;
import com.nielseniq.personalized.data.service.repository.entity.Product;
import com.nielseniq.personalized.data.service.repository.mapper.ProductMapper;
import com.nielseniq.personalized.data.service.repository.model.ProductDTO;
import com.nielseniq.personalized.data.service.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional
    public ProductDTO saveProduct(String productId, String category, String brand) {
        return productMapper.toDTO(productRepository.saveAndFlush(new Product(null,productId, category, brand)));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProductDTO> findByProductId(String productId) {
        Optional<Product> product = productRepository.findByProductId(productId);

        if (product.isPresent()) {
            return Optional.of(productMapper.toDTO(product.get()));
        }

        return Optional.empty();
    }
}
