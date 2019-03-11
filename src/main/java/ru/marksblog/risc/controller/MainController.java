package ru.marksblog.risc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(){
        return "../static/index.html";
    }

    @GetMapping("/chat")
    public String chat(){
        return "../static/chat.html";
    }

    @GetMapping("/static/style.css")
    public String css(){
        return "../static/style.css";
    }
}
