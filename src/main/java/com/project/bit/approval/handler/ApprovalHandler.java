package com.project.bit.approval.handler;

import org.springframework.util.StringUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApprovalHandler extends TextWebSocketHandler {

    //모든 사용자에게 날리기 위해서 접속되어있는 세션을 List에 모두 저장
    List<WebSocketSession> sessions = new ArrayList<>();
    Map<String, WebSocketSession> userSessions = new HashMap<>();


    //클라이언트가 서버 접속 성공시
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        System.out.println("afterConnectionEstablished:" + session);
//        sessions.add(session);
        String senderId = getId(session);
        userSessions.put(senderId, session);
    }

    //소켓에 메시지 전송시
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        System.out.println("handleTextMessage:" + session + " : " + message.getPayload());
//        String senderId = getId(session);
        //받은 메시지를 모든 유저에게 날림
//        for (WebSocketSession sess : sessions) {
//            sess.sendMessage(new TextMessage(senderId + ": " + message.getPayload()));
//        }

        //protocol: cmd,  결재자, 결재문서작성자, 결재문서번호 (approval, jiyoon, test001, 272)
        String msg = message.getPayload();
        if(!StringUtils.isEmpty(msg)){
            String[] strs = msg.split(",");
            if(strs != null && strs.length ==4){
                String cmd = strs[0];
                String approver = strs[1];
                String apDocWriter = strs[2];
                String apDocNo = strs[3];

                WebSocketSession apDocWriterSession = userSessions.get(apDocWriter);

                if("approval".equals(cmd) && apDocWriterSession != null){
                    TextMessage tmpMsg = new TextMessage(approver + "님이 " +
                            "<a href='/approval/getApDoc?apDocNo="+apDocNo+"'>"+apDocNo+"</a>번 결재문서를 결재 처리하였습니다.");
                    apDocWriterSession.sendMessage(tmpMsg);

                }
            }
        }
    }

    //커넥션 종료시
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        System.out.println("afterConnectionEstablished:" + session + ":" + status);
    }

    private String getId(WebSocketSession session) {
        Map<String, Object> httpSesstion = session.getAttributes();
        String user = session.getPrincipal().getName();
//        System.out.println("user아이디 학인------------------------>"+user);

        if(user == null)
            return session.getId();
        else
            return user;
    }
}
