# JSON

Message Object 

{
  "type": "NEWJOIN",
  "conversationId": null,
  "authorId": "",
  "roomNo": "",
  "content": "",
  "creationTime": "",
  "participations": [
    {
      "userId": "user002",
      "conversationId": "2",
      "joinTime": ""
    }
  ],
}

# 문제 해결 과정



### Oracle Database

채팅방 생성시에 채팅방 번호가 1씩 자동으로 늘어나야 한다.

```
Oracle 기준 sequence를 이용

create sequence (시퀀스 이름) start with 1 increment by 1;

drop sequence (시퀀스 이름) 
```

시퀀스 전체 조회 

select  * from user_sequences

### 06.16

로그인한 사람의 채팅방 리스트

- 채팅방별 최근메시지



### 06.17

#### 오늘 할 일

- Cilent 에서 채팅방 다중 구독
- 





### 06.18

오늘 할일

- 채팅 분기 처리



 1:N 대화 시작 메시지 전송

- 채팅방 생성
  - 채팅방 생성

- 대화 Participation 
  - 1:N 대화 처음 Participation 처리
- 메시지 전송
  - 메시지 저장



### 06.22

클라  ->  서버 로 메시지 보낼때 어떤 메시지 형식을 갖춰야할까?



먼저 메시지를 보낼때 고려해야할 경우의수

- 대화 처음 시작해 기존의 대화방이 없는 경우

  - 대화방 개설 JOIN , 해당 대화방 메시지 전송 SEND - 두가지 행위 필요
  - 메시지 보낸사람, 받는 사람 - Participaiton 처리
  - 먼저 Create Conversation 처리후 클라이언트로 ConversationId 전송 후 다시 메시지 SEND 처리 필요
  - DB 처리 - Create Conversation, Create duplex participation, Create Message

  

- 대화 처음 X 기존의 대화방이 있는 경우

  - 대화방 메시지 전송 SEND
  - DB 처리 - Create Message



### 06.23

