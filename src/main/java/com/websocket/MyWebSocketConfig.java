package com.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration  
@EnableWebMvc  
@EnableWebSocket
public class MyWebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
	private static final long heartbeatTime =  60000L; // 1 minute
    @Autowired
    private MyHandler myHandler;
	
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    	registry.addHandler(myHandler, "/websocket").addInterceptors(new MyHandshakeInterceptor());
    	   
    	registry.addHandler(myHandler, "/sockjs/websocket").addInterceptors(new MyHandshakeInterceptor())
    		.withSockJS().setHeartbeatTime(heartbeatTime);
    }

}