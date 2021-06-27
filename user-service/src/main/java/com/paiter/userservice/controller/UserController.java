package com.paiter.userservice.controller;

import com.paiter.userservice.dto.UserDto;
import com.paiter.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public Flux<UserDto> getAll() {
        return this.userService.all();
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<UserDto>> getById(@PathVariable("id") int id) {
        return this.userService.getUserById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<UserDto> createUser(@RequestBody Mono<UserDto> userDtoMono) {
        return this.userService.createUser(userDtoMono);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<UserDto>> updateUser(@PathVariable("id") int id,
                                    final @RequestBody Mono<UserDto> userDtoMono) {
        return this.userService.updateUser(id, userDtoMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteUser(@PathVariable int id) {
        return this.userService.deleteUser(id);
    }

}