package com.forum.project.ForumProject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class WorldHi {

    @GetMapping
    public String hello() {
        return "Se você está aqui significa que está visualizando o inicio de tudo e desse projeto";
    }
}
