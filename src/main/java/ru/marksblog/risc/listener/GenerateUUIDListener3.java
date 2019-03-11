package ru.marksblog.risc.listener;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import ru.marksblog.risc.entity.Channel;
import ru.marksblog.risc.entity.Message;

import java.util.UUID;

public class GenerateUUIDListener3 extends AbstractMongoEventListener<Channel> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Channel> event) {
        Channel channel = event.getSource();
        if (channel.isNew()) {
            channel.setId(UUID.randomUUID());
        }
    }
}
