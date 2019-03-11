package ru.marksblog.risc.listener;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import ru.marksblog.risc.entity.User;

import java.util.UUID;

@Component
public class GenerateUUIDListener extends AbstractMongoEventListener<User> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<User> event) {
        User user = event.getSource();
        if (user.isNew()) {
            user.setId(UUID.randomUUID());
        }
    }

}
