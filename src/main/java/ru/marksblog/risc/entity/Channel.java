package ru.marksblog.risc.entity;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.UUID;

@Document(collection = "channels")
public class Channel  implements Persistable<UUID> {

    @Id
    private UUID id;
    @UniqueElements
    private String channelname;
    @Autowired
    private ArrayList<String> messages;

    public Channel(){

    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean isNew() {
         return (getId() == null);
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getChannelname() {
        return channelname;
    }

    public void setChannelname(String channelname) {
        this.channelname = channelname;
    }

    public ArrayList<String> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<String> messages) {
        this.messages = messages;
    }
}
