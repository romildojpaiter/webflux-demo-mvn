package com.paiter.userservice.event;

import com.paiter.userservice.entity.User;
import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.data.r2dbc.mapping.event.BeforeSaveCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.r2dbc.core.Parameter;
import reactor.core.publisher.Mono;

public class UserBeforeSave implements BeforeSaveCallback<User> {

    @Override
    public Publisher<User> onBeforeSave(User entity, OutboundRow outboundRow, SqlIdentifier table) {
        outboundRow.put(SqlIdentifier.unquoted("created_by"), Parameter.from("vinsguru"));
        return Mono.just(entity);

    }

}