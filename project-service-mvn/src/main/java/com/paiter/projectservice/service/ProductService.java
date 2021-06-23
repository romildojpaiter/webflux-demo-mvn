package com.paiter.projectservice.service;

import com.paiter.projectservice.dto.ProductDto;
import com.paiter.projectservice.repository.ProductRepository;
import com.paiter.projectservice.translators.ProductTranslator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductTranslator translator;

    public Flux<ProductDto> getAll() {
        return this.productRepository.findAll()
                .map(translator::toDto);
    }

    public Flux<ProductDto> getProductsByPriceRange(int min, int max) {
        return this.productRepository.findByPriceBetween(Range.closed(min, max))
                .map(translator::toDto);
    }

    public Mono<ProductDto> getProductById(final String id) {
        return this.productRepository.findById(id)
                .map(translator::toDto);
    }

    public Mono<ProductDto> insertProduct(final Mono<ProductDto> productDtoMono) {
        return productDtoMono
                .map(translator::toEntity)
                .flatMap(this.productRepository::insert)
                .map(translator::toDto);
    }

    public Mono<ProductDto> updateProduct(final String id, Mono<ProductDto> productDtoMono) {
        return this.productRepository.findById(id)
                .flatMap(p -> productDtoMono.map(translator::toEntity)
                        .doOnNext(e -> e.setId(id)))
                .flatMap(this.productRepository::save)
                .map(translator::toDto);
    }

    public Mono<Void> deleteProduct(final String id) {
        return this.productRepository.deleteById(id);
    }

}
