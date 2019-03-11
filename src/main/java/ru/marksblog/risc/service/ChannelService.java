package ru.marksblog.risc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.marksblog.risc.entity.Channel;
import ru.marksblog.risc.entity.User;
import ru.marksblog.risc.interfac.ChannelInterface;
import ru.marksblog.risc.interfac.UserInterface;

import java.util.List;

@Service
public class ChannelService {

    @Autowired
    private ChannelInterface channelInterface;

    @Autowired
    private UserInterface userInterface;

    public Mono<User> updateUserInfoAboutChannel(User user,String channelname){
        Mono<Channel> mono=channelInterface.findChannelByChannelname(channelname);
        Channel ch=mono.block();
        mono.subscribe();
        if(ch!=null && user!=null) {
            Mono <User> mono2=userInterface.updateChannelForUser(user.getNickname(), ch.getChannelname());
            return mono2;
        } else {
            return null;
        }
    }
}
