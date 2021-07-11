package com.paiter.orderservice.service;

import com.paiter.orderservice.client.ProductClient;
import com.paiter.orderservice.client.UserClient;
import com.paiter.orderservice.dto.PurchaseOrderRequestDto;
import com.paiter.orderservice.dto.PurchaseOrderResponseDto;
import com.paiter.orderservice.dto.RequestContext;
import com.paiter.orderservice.repository.PurchaseOrderRepository;
import com.paiter.orderservice.util.EntityDtoUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class OrderFulfillmentService {

    private final PurchaseOrderRepository purchaseOrderRepository;

    @NonNull
    private ProductClient productClient;
    @NonNull
    private UserClient userClient;

    public Mono<PurchaseOrderResponseDto> processOrder(final Mono<PurchaseOrderRequestDto> requestDtoMono) {
        return requestDtoMono.map(RequestContext::new)
                .flatMap(this::productRequestResponse)
                .doOnNext(EntityDtoUtil::setTransactionRequestDto)
                .flatMap(this::userRequestResponse)
                .map(EntityDtoUtil::getPurchaseOrder)
                .map(this.purchaseOrderRepository::save) // blocking
                .map(EntityDtoUtil::getPurchaseOrderResponseDto)
                .subscribeOn(Schedulers.boundedElastic());
    }

    private Mono<RequestContext> productRequestResponse(RequestContext requestContext) {
        return this.productClient.getProductById(requestContext.getPurchaseOrderRequestDto().getProductId())
                .doOnNext(requestContext::setProductDto)
                .thenReturn(requestContext);
    }

    private Mono<RequestContext> userRequestResponse(final RequestContext requestContext) {
        return this.userClient.authorizeTransaction(requestContext.getTransactionRequestDto())
                .doOnNext(requestContext::setTransactionResponseDto)
                .thenReturn(requestContext);
    }
}
