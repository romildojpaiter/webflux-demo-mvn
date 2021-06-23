package com.paiter.webfluxdemo.config;

import com.paiter.webfluxdemo.dto.InputFailedValidationResponse;
import com.paiter.webfluxdemo.dto.MultiplyRequestDto;
import com.paiter.webfluxdemo.dto.Response;
import com.paiter.webfluxdemo.exception.InputValidationException;
import com.paiter.webfluxdemo.services.ReactiveMatchService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class RequestHandler {

    private final ReactiveMatchService matchService;

    public Mono<ServerResponse> squareHandler(ServerRequest serverRequest) {
        var input = Integer.parseInt(serverRequest.pathVariable("input"));
        Mono<Response> response = this.matchService.findSquare(input);
        return ServerResponse.ok().body(response, Response.class);
    }

    public Mono<ServerResponse> tableHandler(ServerRequest serverRequest) {
        var input = Integer.parseInt(serverRequest.pathVariable("input"));
        Flux<Response> response = this.matchService.multiplicationTable(input);
        return ServerResponse.ok().body(response, Response.class);
    }

    public Mono<ServerResponse> tableStreamHandler(ServerRequest serverRequest) {
        var input = Integer.parseInt(serverRequest.pathVariable("input"));
        Flux<Response> response = this.matchService.multiplicationTable(input);
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(response, Response.class);
    }

    public Mono<ServerResponse> multiplyHandler(ServerRequest serverRequest) {
        var input = serverRequest.bodyToMono(MultiplyRequestDto.class);
        Mono<Response> response = this.matchService.multiply(input);
        return ServerResponse.ok()
                .body(response, Response.class);
    }

    public Mono<ServerResponse> squareHandlerWithValidation(ServerRequest serverRequest) {
        var input = Integer.parseInt(serverRequest.pathVariable("input"));
        if (input < 10  || input > 20) {
            return Mono.error(new InputValidationException(input));
        }
        Mono<Response> response = this.matchService.findSquare(input);
        return ServerResponse.ok().body(response, Response.class);
    }

}
