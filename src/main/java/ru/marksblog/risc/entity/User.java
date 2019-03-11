package ru.marksblog.risc.entity;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "users")
public class User implements Persistable<UUID> {

    @Id
    private UUID id;
    @UniqueElements
    private String nickname;
    private String password;
    private String connchannel;
    private boolean isLogin=false;

    public User(){

    }

    public User(String nickname,String password,String connchannel,boolean isLogin){
        this.nickname=nickname;
        this.password=password;
        this.connchannel=connchannel;
        this.isLogin=isLogin;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConnchannel() {
        return connchannel;
    }

    public void setConnchannel(String connchannel) {
        this.connchannel = connchannel;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}
