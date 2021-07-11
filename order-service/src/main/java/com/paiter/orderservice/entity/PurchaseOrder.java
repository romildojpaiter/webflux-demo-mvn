package com.paiter.orderservice.entity;

import com.paiter.orderservice.dto.enums.OrderStatus;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@ToString
public class PurchaseOrder {

    @Id
    @GeneratedValue
    private Integer id;

    private String productId;
    private Integer userId;
    private Integer amount;
    private OrderStatus status;

}
