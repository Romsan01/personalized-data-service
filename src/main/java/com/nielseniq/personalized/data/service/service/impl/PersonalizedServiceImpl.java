package com.nielseniq.personalized.data.service.service.impl;

import com.nielseniq.personalized.data.service.repository.ShelfRepository;
import com.nielseniq.personalized.data.service.repository.entity.Shelf;
import com.nielseniq.personalized.data.service.repository.mapper.ShelfMapper;
import com.nielseniq.personalized.data.service.repository.model.ProductDTO;
import com.nielseniq.personalized.data.service.repository.model.ShopperDTO;
import com.nielseniq.personalized.data.service.service.PersonalizedService;
import com.nielseniq.personalized.data.service.service.ShopperService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonalizedServiceImpl implements PersonalizedService {
    private final ShopperService shopperService;
    private final ShelfRepository shelfRepository;
    private final ShelfMapper shelfMapper;

    public PersonalizedServiceImpl(ShopperService shopperService, ShelfRepository shelfRepository, ShelfMapper shelfMapper) {
        this.shopperService = shopperService;
        this.shelfRepository = shelfRepository;
        this.shelfMapper = shelfMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> findProductsByShopperIdCategoryAndBrand(
            String shopperId, String category, String brand, int limit) {
        List<Shelf> shelves = shelfRepository.findByShopperIdAndProductCategoryAndProductBrand(shopperId, category, brand, PageRequest.of(0, limit));
        return shelves.stream()
                .map(shelf -> shelfMapper.toDTO(shelf).getProduct())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ShopperDTO> findShoppersByProductId(String productId, int limit) {
        List<Shelf> shelves = shelfRepository.findByProductId(productId, PageRequest.of(0, limit));
        return shelves.stream()
                .map(shelf -> shelfMapper.toDTO(shelf).getShopper())
                .distinct()
                .collect(Collectors.toList());
    }
}
