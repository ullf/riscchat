package ru.marksblog.risc.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "messages")
public class Message implements Persistable<UUID> {

    @Id
    ObjectId databaseId;
    private UUID id;
    private String message;
    private String author;
    private String connectedChannel;

    public Message(){

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getConnectedChannel() {
        return connectedChannel;
    }

    public void setConnectedChannel(String connectedChannel) {
        this.connectedChannel = connectedChannel;
    }
}
