package com.paiter.userservice.util;

import com.paiter.userservice.dto.TransactionRequestDto;
import com.paiter.userservice.dto.TransactionResponseDto;
import com.paiter.userservice.dto.UserDto;
import com.paiter.userservice.dto.enums.TransactionStatus;
import com.paiter.userservice.entity.User;
import com.paiter.userservice.entity.UserTransaction;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class EntityDtoUtil {

    public static UserDto toDto(User user) {
        var dto = new UserDto();
        BeanUtils.copyProperties(user, dto);
        return dto;
    }

    public static User toEntity(UserDto userDto) {
        var user = new User();
        BeanUtils.copyProperties(userDto, user);
        return user;
    }

    public static UserTransaction toEntity(TransactionRequestDto transactionRequestDto) {
        var ut = new UserTransaction();
        ut.setUserId(transactionRequestDto.getUserId());
        ut.setAmount(transactionRequestDto.getAmount());
        ut.setTransactionDate(LocalDateTime.now());
        return ut;
    }

    public static TransactionResponseDto toDto(TransactionRequestDto requestDto, TransactionStatus status) {
        var tr = new TransactionResponseDto();
        tr.setAmount(requestDto.getAmount());
        tr.setUserId(requestDto.getUserId());
        tr.setStatus(status);
        return tr;
    }

}
