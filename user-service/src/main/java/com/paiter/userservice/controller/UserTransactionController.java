package com.paiter.userservice.controller;

import com.paiter.userservice.dto.TransactionRequestDto;
import com.paiter.userservice.dto.TransactionResponseDto;
import com.paiter.userservice.entity.UserTransaction;
import com.paiter.userservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
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

    @GetMapping
    public Flux<UserTransaction> getByUserid(@RequestParam("userid") int userId) {
        return this.transactionService.getByUserId(userId);
    }
}
