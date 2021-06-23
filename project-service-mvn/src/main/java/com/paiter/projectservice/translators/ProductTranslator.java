package com.paiter.projectservice.translators;

import com.paiter.projectservice.dto.ProductDto;
import com.paiter.projectservice.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductTranslator extends Translator<Product, ProductDto> {

    ProductTranslator INSTANCE = Mappers.getMapper(ProductTranslator.class);

    @Override
    ProductDto toDto(Product product);

    @Override
    Product toEntity(ProductDto productDto);

}
