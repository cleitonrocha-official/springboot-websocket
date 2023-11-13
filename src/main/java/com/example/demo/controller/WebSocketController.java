package com.example.demo.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class WebSocketController {

    @MessageMapping("/hello") // Endpoint para receber mensagens dos clientes (exemplo: /app/hello)
    @SendTo("/topic/greetings") // Endpoint para enviar mensagens para os clientes que estão inscritos (exemplo: /topic/greetings)
    public Greeting greet(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulado delay
        return new Greeting("Hello, " + message.getName() + "!");
    }
    
    private final SimpMessagingTemplate messagingTemplate;
    private int counter = 0;

    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Scheduled(fixedRate = 5000)
    public void sendPeriodicMessages() {
        counter += 5;
        String message = "Passou " + counter + " segundos";
        log.info(message);
        messagingTemplate.convertAndSend("/topic/greetings", new Greeting(message));
    }
}
