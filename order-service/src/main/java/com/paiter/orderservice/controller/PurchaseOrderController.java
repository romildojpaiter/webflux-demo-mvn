package com.paiter.orderservice.controller;

import com.paiter.orderservice.dto.PurchaseOrderRequestDto;
import com.paiter.orderservice.dto.PurchaseOrderResponseDto;
import com.paiter.orderservice.service.OrderFulfillmentService;
import com.paiter.orderservice.service.OrderQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class PurchaseOrderController {

    private final OrderFulfillmentService orderFulfillmentService;
    private final OrderQueryService orderQueryService;

    @PostMapping
    public Mono<PurchaseOrderResponseDto> order(@RequestBody Mono<PurchaseOrderRequestDto> requestDtoMono) {
        return this.orderFulfillmentService.processOrder(requestDtoMono);
    }

    @GetMapping("user/{id}")
    public Flux<PurchaseOrderResponseDto> getOrdersByUserId(final @PathVariable("id") int userId) {
        return this.orderQueryService.getProductsByUserId(userId);
    }

}
