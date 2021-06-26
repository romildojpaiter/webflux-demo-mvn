package com.paiter.projectservice.translators;

import com.paiter.projectservice.dto.ProductDto;
import com.paiter.projectservice.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductTranslator extends Translator<Product, ProductDto> {

    ProductTranslator INSTANCE = Mappers.getMapper(ProductTranslator.class);

    @Override
    @Mapping(target = "id", source = "id")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", source = "price")
    ProductDto toDto(Product product);

    @Override
    @Mapping(target = "id", source = "id")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", source = "price")
    Product toEntity(ProductDto productDto);

}
