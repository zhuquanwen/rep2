package com.zqw.le.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * websocket配置
 * **/
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/endpointTest").withSockJS();
		
		registry.addEndpoint("/endpointTestUser").withSockJS();
	}
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/topic","/queue");//配置一个广播消息代理,一个单对单用户的代理
		registry.setApplicationDestinationPrefixes("/app");  //客户端发送的前缀
		/*registry.setUserDestinationPrefix("/user/"); //这句表示给指定用户发送（一对一）的主题前缀是“/user/”;  
*/	
	}
}
