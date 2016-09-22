package com.zqw.le.controller;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zqw.le.domain.p.webSocket.ClientMessage;
import com.zqw.le.domain.p.webSocket.ServerMessage;
import com.zqw.le.domain.p.webSocket.UserMessage;

@Controller
public class WebSoketController {
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	@MessageMapping("/welcom")
	//广播
	@SendTo("/topic/send")
	public ServerMessage send1(ClientMessage cm){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ServerMessage sm=new ServerMessage();
		sm.setMessage(cm.getMessage());
		return sm;
		
	}
	//1对1
	@MessageMapping("/message")
	@SendToUser("/queue/notifications")
	public UserMessage handleSpittle(UserMessage um) {
		return um;
	}
	@RequestMapping("/toWs1")
	public ModelAndView toWs1(){
		return new ModelAndView("thymeleaf/websocket/ws1");
	}
	@RequestMapping("/toWs2")
	public ModelAndView toWs2(){
		return new ModelAndView("thymeleaf/websocket/ws2");
	}
	
	@RequestMapping("/toChat")
	public ModelAndView toChat(){
		return new ModelAndView("redirect:/toWs2");
	}
	
	@RequestMapping("/login")
	public ModelAndView toLogin(){
		return new ModelAndView("thymeleaf/websocket/login");
	}
	@MessageMapping("/toUser")
	public void toUser(Principal principal,UserMessage um){
		if("test1".equals(principal.getName())){
			simpMessagingTemplate.convertAndSendToUser("test2", "/queue/notifications", um);
		}else if("test2".equals(principal.getName())){
			simpMessagingTemplate.convertAndSendToUser("test1", "/queue/notifications", um);
		}
	}
}
