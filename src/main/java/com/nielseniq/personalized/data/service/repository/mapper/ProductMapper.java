package com.nielseniq.personalized.data.service.repository.mapper;

import com.nielseniq.personalized.data.service.repository.entity.Product;
import com.nielseniq.personalized.data.service.repository.model.ProductDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toDTO(Product product);
    Product toEntity(ProductDTO productDTO);
}