package com.paiter.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements ReactiveUserDetailsService {

    private final Map<String, UserDetails> map;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return Mono.just(this.map.get(username));
    }

}
