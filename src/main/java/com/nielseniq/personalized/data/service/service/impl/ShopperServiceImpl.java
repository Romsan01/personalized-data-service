package com.nielseniq.personalized.data.service.service.impl;

import com.nielseniq.personalized.data.service.repository.ShopperRepository;
import com.nielseniq.personalized.data.service.repository.entity.Shopper;
import com.nielseniq.personalized.data.service.repository.mapper.ShopperMapper;
import com.nielseniq.personalized.data.service.repository.model.ShopperDTO;
import com.nielseniq.personalized.data.service.service.ShopperService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ShopperServiceImpl implements ShopperService {

    private final ShopperRepository shopperRepository;
    private final ShopperMapper shopperMapper;

    public ShopperServiceImpl(ShopperRepository shopperRepository, ShopperMapper shopperMapper) {
        this.shopperRepository = shopperRepository;
        this.shopperMapper = shopperMapper;
    }

    @Override
    @Transactional
    public ShopperDTO saveShopper(String shopperId) {
        return shopperMapper.toDTO(shopperRepository.saveAndFlush(new Shopper(null, shopperId)));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ShopperDTO> findByShopperId(String shopperId) {
        Optional<Shopper> shopper = shopperRepository.findByShopperId(shopperId);

        if (shopper.isPresent()) {
            return Optional.of(shopperMapper.toDTO(shopper.get()));
        }

        return Optional.empty();
    }
}
