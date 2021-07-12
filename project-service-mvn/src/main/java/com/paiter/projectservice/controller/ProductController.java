package com.paiter.projectservice.controller;

import com.paiter.projectservice.dto.ProductDto;
import com.paiter.projectservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("all")
    public Flux<ProductDto> getAll() {
        return this.productService.getAll();
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<ProductDto>> getProductById(final @PathVariable("id") String id) {
        this.simulateRandomException();
        return this.productService.getProductById(id).map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("price-range")
    public Flux<ProductDto> getProductsByPriceRange(final @RequestParam("min") int min,
                                                    final @RequestParam("max") int max) {
        return this.productService.getProductsByPriceRange(min, max);
    }

    @PostMapping
    public Mono<ProductDto> insertProduct(final @RequestBody Mono<ProductDto> productDtoMono) {
        return this.productService.insertProduct(productDtoMono);
    }

    @PostMapping("{id}")
    public Mono<ResponseEntity<ProductDto>> updateProduct(final @PathVariable("id") String id,
                                                          final @Validated @RequestBody Mono<ProductDto> productDtoMono) {
        return this.productService.updateProduct(id, productDtoMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteProduct(final @PathVariable("id") String id) {
        assert id.length() > 0 : "Conteudo do id não dever ser vazio";
        return this.productService.deleteProduct(id);
    }

    private void simulateRandomException() {
        int threadLocalRandom = ThreadLocalRandom.current().nextInt(1, 10);
        if (threadLocalRandom > 5) {
            throw new RuntimeException("something is wrong");
        }
    }


}
