package com.paiter.webfluxdemo;

import com.paiter.webfluxdemo.dto.MultiplyRequestDto;
import com.paiter.webfluxdemo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class Lec08AttributiesTestextends extends BaseTest {

    @Autowired
    private WebClient webClient;

    @Test
    public void headerTest() {
        Mono<Response> responseMono = this.webClient
                .post()
                .uri("reactive-math/multiply")
                .bodyValue(buildRequestDto(5, 10))
                .attribute("auth", "oauth")
                // .attribute("auth", "basic")
                .retrieve()
                .bodyToMono(Response.class)
                .doOnNext(System.out::println);

        StepVerifier.create(responseMono)
                .expectNextCount(1)
                // .expectNextMatches(r -> r.getOutput() == 50)
                .verifyComplete();
    }

    @Test
    public void autenticationTest() {
        Mono<Response> responseMono = this.webClient
                .post()
                .uri("reactive-math/multiply")
                .bodyValue(buildRequestDto(5, 10))
                .headers(h -> h.setBasicAuth("username", "password"))
                .retrieve()
                .bodyToMono(Response.class)
                .doOnNext(System.out::println);

        StepVerifier.create(responseMono)
                .expectNextCount(1)
                // .expectNextMatches(r -> r.getOutput() == 50)
                .verifyComplete();
    }

    private MultiplyRequestDto buildRequestDto(int a, int b) {
        var multiply = new MultiplyRequestDto();
        multiply.setFirst(a);
        multiply.setSecond(b);
        return  multiply;
    }
}