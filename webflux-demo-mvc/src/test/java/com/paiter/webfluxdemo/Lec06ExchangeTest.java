package com.paiter.webfluxdemo;

import com.paiter.webfluxdemo.dto.InputFailedValidationResponse;
import com.paiter.webfluxdemo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec06ExchangeTest extends BaseTest {

    @Autowired
    private WebClient webClient;

    // exchange = retrive + aditional info http status code
    @Test
    public void exchangeTest() {

        Mono<Object> responseMono = this.webClient
                .get()
                .uri("reactive-math/square/{input}/throw", 5)
                .exchangeToMono(this::exchange)
                .doOnNext(System.out::println)
                .doOnError(err -> System.out.println(err.getMessage()));

        StepVerifier.create(responseMono)
                .expectNextCount(1)
                .verifyComplete();

    }

    private Mono<Object> exchange(ClientResponse client) {
        if (client.rawStatusCode() == 400) {
            return client.bodyToMono(InputFailedValidationResponse.class);
        } else
            return client.bodyToMono(Response.class);
    }

}
