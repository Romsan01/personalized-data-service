package com.nielseniq.personalized.data.service.service.impl;

import com.nielseniq.personalized.data.service.repository.ShelfRepository;
import com.nielseniq.personalized.data.service.repository.entity.Shelf;
import com.nielseniq.personalized.data.service.repository.mapper.ProductMapper;
import com.nielseniq.personalized.data.service.repository.mapper.ShelfMapper;
import com.nielseniq.personalized.data.service.repository.mapper.ShopperMapper;
import com.nielseniq.personalized.data.service.repository.model.ProductDTO;
import com.nielseniq.personalized.data.service.repository.model.ShelfDTO;
import com.nielseniq.personalized.data.service.repository.model.ShopperDTO;
import com.nielseniq.personalized.data.service.service.ShelfService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ShelfServiceImpl implements ShelfService {
    private final ShelfRepository shelfRepository;
    private final ShelfMapper shelfMapper;
    private final ProductMapper productMapper;
    private final ShopperMapper shopperMapper;

    public ShelfServiceImpl(ShelfRepository shelfRepository, ShelfMapper shelfMapper,ProductMapper productMapper,ShopperMapper shopperMapper) {
        this.shelfRepository = shelfRepository;
        this.shelfMapper = shelfMapper;
        this.productMapper = productMapper;
        this.shopperMapper = shopperMapper;
    }

    @Override
    @Transactional
    public ShelfDTO saveShelf(ShopperDTO shopper, ProductDTO product, Double relevancyScore) {
        Shelf shelf = new Shelf();
        shelf.setProduct(productMapper.toEntity(product));
        shelf.setShopper(shopperMapper.toEntity(shopper));
        shelf.setRelevancyScore(relevancyScore);
        return shelfMapper.toDTO(shelfRepository.saveAndFlush(shelf));
    }
}
