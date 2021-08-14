package com.paiter.orderservice;

import com.paiter.orderservice.client.ProductClient;
import com.paiter.orderservice.client.UserClient;
import com.paiter.orderservice.dto.ProductDto;
import com.paiter.orderservice.dto.PurchaseOrderRequestDto;
import com.paiter.orderservice.dto.PurchaseOrderResponseDto;
import com.paiter.orderservice.dto.UserDto;
import com.paiter.orderservice.service.OrderFulfillmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class OrderServiceApplicationTests {

    @Autowired
    private UserClient userClient;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private OrderFulfillmentService orderFulfillmentService;

    @Test
    void contextLoads() {

        Flux<PurchaseOrderResponseDto> purchaseOrderResponseDtoFlux = Flux.zip(userClient.getAllUsers(), productClient.getAllProducts())
                .map(t -> buildDto(t.getT1(), t.getT2()))
                .flatMap(dto -> this.orderFulfillmentService.processOrder(Mono.just(dto)))
                .doOnNext(System.out::println);

        StepVerifier.create(purchaseOrderResponseDtoFlux)
                .expectNextCount(4)
                .verifyComplete(); 
    }

    private PurchaseOrderRequestDto buildDto(UserDto userDto, ProductDto productDto) {
        var dto = new PurchaseOrderRequestDto();
        dto.setUserId(userDto.getId());
        dto.setProductId(productDto.getId());
        return dto;
    }

}
