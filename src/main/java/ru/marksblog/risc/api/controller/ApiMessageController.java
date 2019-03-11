package ru.marksblog.risc.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import ru.marksblog.risc.entity.Channel;
import ru.marksblog.risc.entity.Message;
import ru.marksblog.risc.entity.User;
import ru.marksblog.risc.interfac.ChannelInterface;
import ru.marksblog.risc.interfac.MessageInterface;

import java.util.List;

@RestController
public class ApiMessageController {

    @Autowired
    private MessageInterface messageInterface;

    @Autowired
    private ChannelInterface channelInterface;

    @GetMapping(value = "/api/findAllMessages",produces = "application/json")
    @ResponseBody
    public List<Message> findAll(){
        return messageInterface.findAll().collectList().block();
    }

    @PostMapping(value = "/api/sendMessage",consumes = "application/json",produces = "application/json")
    @ResponseBody
    public List<Message> sendMessage(@RequestBody Message message){
        messageInterface.save(message).subscribe();
        return messageInterface.findAll().collectList().block();
    }

    @GetMapping(value = "/api/findAllChannels",produces = "application/json")
    @ResponseBody
    public List<Channel> findAllChannels(){
        return channelInterface.findAll().collectList().block();
    }


}
