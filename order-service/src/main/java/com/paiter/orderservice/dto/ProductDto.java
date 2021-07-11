package com.paiter.orderservice.dto;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private String id;
    private String description;
    private Integer price;

}
