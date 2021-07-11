package com.paiter.orderservice.dto;

import com.paiter.orderservice.dto.enums.TransactionStatus;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TransactionResponseDto {

    private Integer userId;
    private Integer amount;
    private TransactionStatus status;

}
