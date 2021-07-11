package com.paiter.orderservice.dto;

import com.paiter.orderservice.dto.enums.OrderStatus;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PurchaseOrderResponseDto {

    private Integer orderId;
    private Integer userId;
    private String productId;
    private Integer amount;
    private OrderStatus status;

}
