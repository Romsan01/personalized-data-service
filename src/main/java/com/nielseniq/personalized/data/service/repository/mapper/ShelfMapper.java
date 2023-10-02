package com.nielseniq.personalized.data.service.repository.mapper;

import com.nielseniq.personalized.data.service.repository.entity.Shelf;
import com.nielseniq.personalized.data.service.repository.model.ShelfDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ShopperMapper.class, ProductMapper.class})
public interface ShelfMapper {
    @Mapping(source = "shopper", target = "shopper")
    @Mapping(source = "product", target = "product")
    ShelfDTO toDTO(Shelf shelf);

    @Mapping(source = "shopper", target = "shopper")
    @Mapping(source = "product", target = "product")
    Shelf toEntity(ShelfDTO shelfDTO);
}