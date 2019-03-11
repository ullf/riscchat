package ru.marksblog.risc.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.marksblog.risc.entity.Message;
import ru.marksblog.risc.entity.User;
import ru.marksblog.risc.interfac.MessageInterface;
import ru.marksblog.risc.interfac.UserInterface;

import java.util.List;

@RestController
public class WebApiController {

    @Autowired
    private MessageInterface messageInterface;

    @Autowired
    private UserInterface userInterface;

    @MessageMapping("/webapi")
    @SendTo("/mess/findAll")
    public List<Message> findAll(){
        return messageInterface.findAll().collectList().block();
    }

    @MessageMapping("/webapisend")
    @SendTo("/mess/send")
    public List<Message> sendMessage(@RequestBody Message message){
        String tmp=userInterface.findUserByNickname(message.getAuthor()).block().getConnchannel();
        message.setConnectedChannel(tmp);
        messageInterface.save(message).subscribe();
        Flux<Message> messages=messageInterface.findMessagesByConnectedChannel(tmp);
        return messages.collectList().block();
    }

    @MessageMapping("/webapigetmessages")
    @SendTo("/mess/getmessages")
    public List<Message> getMessages(@RequestBody Message message){
        String tmp=userInterface.findUserByNickname(message.getAuthor()).block().getConnchannel();
        Flux<Message> messages=messageInterface.findMessagesByConnectedChannel(tmp);
        return messages.collectList().block();
    }
}
