package com.paiter.userservice.controller;

import com.paiter.userservice.dto.TransactionRequestDto;
import com.paiter.userservice.dto.TransactionResponseDto;
import com.paiter.userservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("users/transactions")
@RequiredArgsConstructor
public class UserTransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public Mono<TransactionResponseDto> createTransaction(@RequestBody Mono<TransactionRequestDto> transactionRequestDtoMono) {
        return transactionRequestDtoMono.flatMap(this.transactionService::createTransacion);
    }
}
