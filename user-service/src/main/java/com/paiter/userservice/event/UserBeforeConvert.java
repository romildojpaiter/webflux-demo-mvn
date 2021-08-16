package com.paiter.userservice.event;

import com.paiter.userservice.entity.User;
import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.mapping.event.BeforeConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UserBeforeConvert implements BeforeConvertCallback<User> {

    private static final String PATTERN = "[^a-zA-Z ]";

    @Override
    public Publisher<User> onBeforeConvert(User entity, SqlIdentifier table) {
        var updatedDescription = entity.getDescription().replaceAll(PATTERN, "");
        System.out.println("Actual : " + entity.getDescription());
        System.out.println("Updated : " + updatedDescription);
        entity.setDescription(updatedDescription);
        return Mono.just(entity);
    }
}
