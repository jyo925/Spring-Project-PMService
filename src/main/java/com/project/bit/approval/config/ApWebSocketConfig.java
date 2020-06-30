package com.project.bit.approval.config;

import com.project.bit.approval.handler.ApprovalHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class ApWebSocketConfig implements WebSocketConfigurer {


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        //setAllowedOrigins 안걸어주면 오류 발생할 수 있음
        //아래 url주소에 연결이 되면 소켓에 설정된 메소드들이 실행됨
        webSocketHandlerRegistry.addHandler(new ApprovalHandler(), "approval/approvalGet").setAllowedOrigins("*");
    }
}
