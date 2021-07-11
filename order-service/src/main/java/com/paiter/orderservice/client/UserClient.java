package com.paiter.orderservice.client;

import com.paiter.orderservice.dto.ProductDto;
import com.paiter.orderservice.dto.TransactionRequestDto;
import com.paiter.orderservice.dto.TransactionResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserClient {

    private WebClient webClient;

    /**
     * Constructor with default User URL
     * @param userUrl String
     */
    public UserClient(@Value("${user.service.url}") String userUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(userUrl)
                .build();
    }

    public Mono<TransactionResponseDto> authorizeTransaction(final TransactionRequestDto transactionRequestDto) {
        return this.webClient
                .post()
                .uri("transaction")
                .bodyValue(transactionRequestDto)
                .retrieve()
                .bodyToMono(TransactionResponseDto.class);
    }

}
