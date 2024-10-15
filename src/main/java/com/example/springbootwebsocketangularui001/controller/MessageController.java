package com.example.springbootwebsocketangularui001.controller;

import java.security.Principal;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.example.springbootwebsocketangularui001.dto.Message;

@Controller
public class MessageController {

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public Message getMessage(final Message message){
        message.setMessageContent(HtmlUtils.htmlEscape(message.getMessageContent()));
        System.out.println("!!! Message from BACKEND " + message.getMessageContent());

        return message;
    }

    @MessageMapping("/private-message")
    @SendToUser("/topic/private-messages")
    public Message getPrivateMessage(final Message message, final Principal principal){
        message.setMessageContent(HtmlUtils.htmlEscape(principal.getName() +" %% "+message.getMessageContent()));
        System.out.println("!!! Private Message from BACKEND " + message.getMessageContent());

        return message;
    }

}
