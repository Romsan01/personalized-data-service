package com.nielseniq.personalized.data.service.web;

import com.nielseniq.personalized.data.service.repository.model.ProductDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductResponse {
    private List<ProductDTO> products;
}
