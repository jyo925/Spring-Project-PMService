package com.project.bit.approval.config;

import com.project.bit.approval.handler.ApprovalHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class ApWebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        //setAllowedOrigins 없으면 오류 발생할 수 있음
        //Configure a WebSocketHandler at the specified URL paths.
        webSocketHandlerRegistry.addHandler(new ApprovalHandler(), "approval/approvalGet").setAllowedOrigins("*");

    }
}
