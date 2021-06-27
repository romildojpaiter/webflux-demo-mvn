package com.paiter.userservice.service;

import com.paiter.userservice.dto.UserDto;
import com.paiter.userservice.repository.UserRepository;
import com.paiter.userservice.util.EntityDtoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Flux<UserDto> all() {
        return this.userRepository.findAll().map(EntityDtoUtil::toDto);
    }

    public Mono<UserDto> getUserById(Integer id) {
        return this.userRepository.findById(id).map(EntityDtoUtil::toDto);
    }

    public Mono<UserDto> createUser(final Mono<UserDto> userDtoMono) {
        return userDtoMono
                .map(EntityDtoUtil::toEntity)
                .flatMap(this.userRepository::save)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<UserDto> updateUser(int id, final Mono<UserDto> userDtoMono) {
        return this.userRepository.findById(id)
                .flatMap(user -> userDtoMono.map(EntityDtoUtil::toEntity).doOnNext(e -> e.setId(id)))
                .map(EntityDtoUtil::toDto);
    }

    public Mono<Void> deleteUser(int id) {
        return this.userRepository.deleteById(id);
    }

}
