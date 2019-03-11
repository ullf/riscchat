package ru.marksblog.risc.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.marksblog.risc.entity.User;
import ru.marksblog.risc.interfac.UserInterface;

import java.util.List;

@RestController
public class ApiUserController {

    @Autowired
    private UserInterface userInterface;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping(value = "/api/findAll",produces = "application/json")
    @ResponseBody
    public List<User> findAll(){
        return userInterface.findAll().collectList().block();
    }

    @PostMapping(value = "/api/save",consumes="application/json",produces = "application/json")
    @ResponseBody
    public String save(@RequestBody User param){
        if(userInterface.findUserByNickname(param.getNickname()).block()==null){
            param.setPassword(bCryptPasswordEncoder.encode(param.getPassword()));
            userInterface.save(param).subscribe();
            return "true";
        }else {
            return "false";
        }
    }

    @PostMapping("/api/login")
    @ResponseBody
    public String loginUser(@RequestBody User user){
        String nickname=user.getNickname();
        boolean password=bCryptPasswordEncoder.matches(user.getPassword(),userInterface.findUserByNickname(user.getNickname()).block().getPassword());
        Mono<User> mono=userInterface.findUserByNickname(nickname);
        if(mono.block() != null && password==true && mono.block().getNickname().equals(nickname)) {
            if(mono.block().isLogin()==false){
                user.setLogin(true);
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                user.setId(mono.block().getId());
                userInterface.save(user).subscribe();
                return "true";
            }
            Mono<User> mono2=userInterface.findUserByNickname(nickname);
            if(mono2.block().isLogin()==true){
                return "false";
            }
        }
        return "false";
    }

    @PostMapping(value = "/api/usersconnected",consumes = "application/json",produces = "application/json")
    @ResponseBody
    public List<User> findAllConnected(@RequestBody String author){
        User user=userInterface.findUserByNickname(author.split("=")[1]).block();
        System.out.println(user.getConnchannel());
        Flux<User> flux=userInterface.findAllByConnectedChannel(user.getConnchannel());
        return flux.collectList().block();
    }

    @PostMapping(value = "/api/logout", consumes="application/json",produces = "application/json")
    @ResponseBody
    public User logout(@RequestBody String author) {
        System.out.println(author);
        User user=userInterface.findUserByNickname(author.split("=")[1]).block();
        user.setLogin(false);
        user.setConnchannel(null);
        System.out.println(user.isLogin());
        Mono<User> mono = userInterface.save(user);
        if(!user.getNickname().equals(null)) {
            mono.subscribe();
        }
        return mono.block();
    }

    @PostMapping(value = "/api/check", consumes="application/json",produces = "application/json")
    @ResponseBody
    public User check(@RequestBody String author) {
        System.out.println(author);
        Mono<User> mono = userInterface.findUserByNickname(author.split("=")[1]);
        System.out.println(mono.block().isLogin());
        return mono.block();
    }
}
