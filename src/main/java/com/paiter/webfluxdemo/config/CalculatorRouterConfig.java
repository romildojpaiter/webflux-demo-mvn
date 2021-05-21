package com.paiter.webfluxdemo.config;

import com.paiter.webfluxdemo.exception.InputValidationException;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

@Configuration
@AllArgsConstructor
public class CalculatorRouterConfig {

    private final CalculatorHandler handler;

    @Bean
    public RouterFunction<ServerResponse> calculatorHighLevelRouter() {
        return RouterFunctions.route()
                .path("calculator", this::serverResponseRouterFunction)
                .build();
    }

    private RouterFunction<ServerResponse> serverResponseRouterFunction() {
        return RouterFunctions.route()
                .GET("{a}/{b}", this.isOperation("+"), handler::additingHandler)
                .GET("{a}/{b}", this.isOperation("-"), handler::subtrationHandler)
                .GET("{a}/{b}", this.isOperation("*"), handler::multiplicationHandler)
                .GET("{a}/{b}", this.isOperation("/"), handler::divisionHandler)
                .GET("{a}/{b}", req -> ServerResponse.badRequest().bodyValue("OP should be: + - * /"))
                .build();
    }

    private RequestPredicate isOperation(String operation) {
        return RequestPredicates
                .headers(headers -> operation.equals(headers.asHttpHeaders()
                .toSingleValueMap()
                .get("OP")));
    }
}
