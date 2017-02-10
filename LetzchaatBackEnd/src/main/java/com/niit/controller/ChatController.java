package com.niit.controller;

import java.util.Date;

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import com.niit.model.Message;
import com.niit.model.OutputMessage;

@Controller
public class ChatController {
	
	
	
	@Autowired
	private SimpMessagingTemplate simpmessagingtemplate;
	
	
	/*@MessageMapping("/chat")
	@SendToUser("/queue/message/{friend_name}")
	public void sendMessage(Message message){
		System.out.println("Friend name"+message.getFriend_name());
		System.out.println("Message is"+message.getMessage());
		simpmessagingtemplate.convertAndSendToUser(message.getFriend_name(), "/queue/message",message.getMessage());
		
	}*/
	
	
	@MessageMapping("/chat")
	@SendTo("/queue/message")
	public OutputMessage sendprivateMessage(Message message){
		
		
		return new OutputMessage(message, new Date());
	}
	
	
	
	
	

}
