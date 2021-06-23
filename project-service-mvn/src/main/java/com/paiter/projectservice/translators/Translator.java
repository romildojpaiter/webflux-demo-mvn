package com.paiter.projectservice.translators;

public interface Translator<A, B> {

    B toDto(A entity);
    A toEntity(B dto);

}
