package com.paiter.userservice.event;

import com.paiter.userservice.entity.User;
import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.mapping.event.AfterConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UserAfterConvert implements AfterConvertCallback<User> {

    private static final double seasonalDiscount = 0.2d;

    @Override
    public Publisher<User> onAfterConvert(User user, SqlIdentifier table) {
        double price = (user.getPrice() * (1-seasonalDiscount));
        System.out.println("Actual  : " + user.getPrice());
        System.out.println("Updated : " + price);
        user.setPrice(price);
        return Mono.just(user);
    }

}
