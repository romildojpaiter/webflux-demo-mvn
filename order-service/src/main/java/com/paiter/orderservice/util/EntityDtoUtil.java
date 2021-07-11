package com.paiter.orderservice.util;

import com.paiter.orderservice.dto.PurchaseOrderResponseDto;
import com.paiter.orderservice.dto.RequestContext;
import com.paiter.orderservice.dto.TransactionRequestDto;
import com.paiter.orderservice.dto.enums.OrderStatus;
import com.paiter.orderservice.dto.enums.TransactionStatus;
import com.paiter.orderservice.entity.PurchaseOrder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntityDtoUtil {

    public static void setTransactionRequestDto(RequestContext requestContext) {
        var dto = new TransactionRequestDto();
        dto.setUserId(requestContext.getPurchaseOrderRequestDto().getUserId());
        dto.setAmount(requestContext.getProductDto().getPrice());
        requestContext.setTransactionRequestDto(dto);
    }

    public static PurchaseOrder getPurchaseOrder(final RequestContext requestContext) {
        var purchaseOrder = new PurchaseOrder();
        purchaseOrder.setUserId(requestContext.getPurchaseOrderRequestDto().getUserId());
        purchaseOrder.setProductId(requestContext.getPurchaseOrderRequestDto().getProductId());
        purchaseOrder.setAmount(requestContext.getProductDto().getPrice());
        TransactionStatus status = requestContext.getTransactionResponseDto().getStatus();
        var statusOrder = TransactionStatus.APPROVED.equals(status) ? OrderStatus.COMPLETED : OrderStatus.FAILED;
        purchaseOrder.setStatus(statusOrder);
        return purchaseOrder;
    }

    public static PurchaseOrderResponseDto getPurchaseOrderResponseDto(PurchaseOrder purchaseOrder) {
        var dto = new PurchaseOrderResponseDto();
        BeanUtils.copyProperties(purchaseOrder, dto);
        dto.setOrderId(purchaseOrder.getId());
        return dto;
    }

}
