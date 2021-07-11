package com.paiter.orderservice.service;

import com.paiter.orderservice.dto.PurchaseOrderResponseDto;
import com.paiter.orderservice.repository.PurchaseOrderRepository;
import com.paiter.orderservice.util.EntityDtoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class OrderQueryService {

    private final PurchaseOrderRepository purchaseOrderRepository;

    public Flux<PurchaseOrderResponseDto> getProductsByUserId(int userId) {
        return Flux.fromStream(() -> this.purchaseOrderRepository.findByUserId(userId).stream())
                .map(EntityDtoUtil::getPurchaseOrderResponseDto)
                .subscribeOn(Schedulers.boundedElastic());
    }

}
