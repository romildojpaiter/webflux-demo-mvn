package br.com.paiter.stockquotesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;


@SpringBootApplication
//@ComponentScan(basePackages = "br.com.paiter.stockquotesapi")
// @EnableR2dbcRepositories("br.com.paiter.stockquotesapi")
@EnableAutoConfiguration(exclude = {WebMvcAutoConfiguration.class })
public class StockQuotesApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockQuotesApiApplication.class, args);
    }

}
