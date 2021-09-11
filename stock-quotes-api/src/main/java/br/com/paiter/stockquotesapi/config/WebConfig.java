package br.com.paiter.stockquotesapi.config;

import br.com.paiter.stockquotesapi.handler.QuoteHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@EnableWebFlux
public class WebConfig {

    @Bean
    public RouterFunction<ServerResponse> routeQuotes(QuoteHandler quoteHandler) {
        return route(RequestPredicates.GET("v2/quotes"), quoteHandler::getAllQuotes);
    }

}
