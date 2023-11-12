package com.example.demo.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/hello") // Endpoint para receber mensagens dos clientes (exemplo: /app/hello)
    @SendTo("/topic/greetings") // Endpoint para enviar mensagens para os clientes que estão inscritos (exemplo: /topic/greetings)
    public Greeting greet(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulado delay
        return new Greeting("Hello, " + message.getName() + "!");
    }
}
