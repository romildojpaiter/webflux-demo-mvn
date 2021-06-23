package com.paiter.projectservice.util;

import com.paiter.projectservice.dto.ProductDto;
import com.paiter.projectservice.entity.Product;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor
public final class EntityDtoUtil {

    private static ProductDto toDto(Product product) {
        var dto = new ProductDto();
        BeanUtils.copyProperties(product, dto);
        return dto;
    }

    private static Product toEntity(ProductDto productDto) {
        var entity = new Product();
        BeanUtils.copyProperties(productDto, entity);
        return entity;
    }
}
