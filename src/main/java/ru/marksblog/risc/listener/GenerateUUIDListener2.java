package ru.marksblog.risc.listener;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import ru.marksblog.risc.entity.Message;

import java.util.UUID;

@Component
public class GenerateUUIDListener2 extends AbstractMongoEventListener<Message> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Message> event) {
        Message message = event.getSource();
        if (message.isNew()) {
            message.setId(UUID.randomUUID());
        }
    }

}
