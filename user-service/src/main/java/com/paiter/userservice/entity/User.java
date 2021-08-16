package com.paiter.userservice.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@ToString
@Table("users")
public class User {

    @Id
    private Integer id;
    private String name;
    private String description;
    private Integer balance;
    private Double price;

}

