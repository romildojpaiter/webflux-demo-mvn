package com.paiter.userservice.service;

import com.paiter.userservice.dto.TransactionRequestDto;
import com.paiter.userservice.dto.TransactionResponseDto;
import com.paiter.userservice.dto.enums.TransactionStatus;
import com.paiter.userservice.entity.UserTransaction;
import com.paiter.userservice.repository.UserRepository;
import com.paiter.userservice.repository.UserTransactionRepository;
import com.paiter.userservice.util.EntityDtoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final UserRepository userRepository;
    private final UserTransactionRepository userTransactionRepository;

    public Mono<TransactionResponseDto> createTransacion(final TransactionRequestDto transactionRequestDto) {
        return this.userRepository.updateUserBalance(transactionRequestDto.getUserId(), transactionRequestDto.getAmount())
                .filter(Boolean::booleanValue)
                .map(res -> EntityDtoUtil.toEntity(transactionRequestDto))
                .flatMap(this.userTransactionRepository::save)
                .map(ut -> EntityDtoUtil.toDto(transactionRequestDto, TransactionStatus.APPROVED))
                .defaultIfEmpty(EntityDtoUtil.toDto(transactionRequestDto, TransactionStatus.DECLINED));
    }

    public Flux<UserTransaction> getByUserId(int userid){
        return this.userTransactionRepository.findByUserId(userid);
    }

}
