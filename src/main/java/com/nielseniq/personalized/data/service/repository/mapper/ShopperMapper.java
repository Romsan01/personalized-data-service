package com.nielseniq.personalized.data.service.repository.mapper;

import com.nielseniq.personalized.data.service.repository.entity.Shopper;
import com.nielseniq.personalized.data.service.repository.model.ShopperDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShopperMapper {
    ShopperDTO toDTO(Shopper shopper);
    Shopper toEntity(ShopperDTO shopperDTO);
}