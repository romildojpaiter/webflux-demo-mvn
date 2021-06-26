package com.paiter.projectservice.service;

import com.paiter.projectservice.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DataSetupService implements CommandLineRunner {

    private final ProductService productService;

    @Override
    public void run(String... args) throws Exception {

        var productDto = ProductDto.builder().price(10).description("Caneta").build();
        var productDto1 = ProductDto.builder().price(9).description("Caneta Azul").build();
        var productDto2 = ProductDto.builder().price(8).description("Caneta Red").build();
        var productDto3 = ProductDto.builder().price(7).description("Caneta Green").build();

        Flux.just(productDto, productDto1, productDto2, productDto3)
                .flatMap(product -> this.productService.insertProduct(Mono.just(product)))
                .subscribe(System.out::println);
    }

}
