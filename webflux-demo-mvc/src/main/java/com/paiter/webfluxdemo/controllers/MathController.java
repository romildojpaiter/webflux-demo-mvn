package com.paiter.webfluxdemo.controllers;

import com.paiter.webfluxdemo.dto.Response;
import com.paiter.webfluxdemo.services.MathService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("math")
@AllArgsConstructor
public class MathController {

    private final MathService mathService;

    @GetMapping("square/{input}")
    public Response findSquare(@PathVariable int input) {
        return this.mathService.findSquare(input);
    }

    @GetMapping("table/{input}")
    public List<Response> multiplicationTable(@PathVariable int input) {
        return this.mathService.multiplicateTable(input);
    }


}
