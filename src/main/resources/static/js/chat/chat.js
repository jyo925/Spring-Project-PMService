/* Util Function */
/* Util Function */

async function getData(url='') {
  const response = await fetch(url, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
    },
  });
  return response.json();
}

async function postData(url = '', data = {}){
  const response = await fetch(url, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(data),
  });
  return response.json();
}

/* Util Function */
/* Util Function */


/* 메신저 연결 버튼 */
const btn_connect = document.querySelector(".messenger-connect");
btn_connect.addEventListener("click", connect);

let stompClient = null;

/* 메신저 연결 호출 */
function connect() {
  let socket = new SockJS("/endpoint");
  stompClient = Stomp.over(socket);
  stompClient.connect({}, function (frame) {
    console.log("Connected" + frame);
    getData("http://localhost:8080/chat/initial")
      .then( data => data.ChatRooms)
      .then( chatRooms => chatRooms.map( chat => {
          console.log(chat);
          document.querySelector(".chat-list").append(UserListTag(chat));
          stompClient.subscribe("/topic/room/"+chat.roomNo, function (response) {
            const message = JSON.parse(response.body);
            document.querySelector(`p[data-room-no="${chat.roomNo}"]`)
                .innerHTML = message.content;
            console.log(response);
          });
        })
      );
  });
}

function UserListTag(chat) {
    const userListTag = document.createElement("div");
    userListTag.className = "list active";
    userListTag.dataset.roomNo = chat.roomNo;
    userListTag.innerHTML =
        '<div class="profile"><img src="" alt="image"><span class="online"></span></div>\n' +
        '  <div class="info">\n' +
        '      <p>' + chat.roomNo + '</p>\n' +
        '      <p class="last-message" data-room-no=' + chat.roomNo + '>' + chat.content + '</p>\n' +
        '  </div>\n' +
        '  <small class="text-muted my-auto">19 min</small>';
    return userListTag;
}

const btn_fetchUserList = document.getElementById('fetchUserList');
btn_fetchUserList.addEventListener("click", fetchUserList);

/* 기능 - 채팅 유저 초대 목록 띄우기 */
function fetchUserList() {
  getData("http://localhost:8080/chat/invite")
      .then( data => {
        console.log(data);
        data.map( user => {
          const user_list = document.querySelector("#user-list");
          let userListDiv = document.createElement("li");
          userListDiv.className = "userList";
          userListDiv.innerHTML = user.userId;
          userListDiv.addEventListener("click", addParticipations);
          user_list.append(userListDiv);
        });
      });
}

const btn_invite = document.getElementById("invite");
btn_invite.addEventListener("click", invite);

/* 기능 - 대화 상대 초대하기 */
function invite() {
  postData("http://localhost:8080/chat/invite", message)
      .then( data => {
        const chatroom_userList = document.querySelector("#chatroom-userlist");
        const chatroom_content = document.querySelector("#chatroom-content");
        console.log(data);
        chatroom_content.dataset.roomNo = data.roomNo;
        data.participations.map( user => {
          console.log(user);
          chatroom_userList.innerHTML += user.userId + "참여했구려";
        });
        stompClient.subscribe("/topic/room/"+ data.roomNo, function(response) {
          /* 받은 메시지 띄우기*/
          const Message = JSON.parse(response.body);
          const chatroom_content = document.querySelector("#chatroom-content");
          chatroom_content.append(MessageTag(Message));
        });
      });
}

/* 채팅 내용 동적 태그 생성 함수 */
function MessageTag(message) {
    const MessageTag = document.createElement("div");
    MessageTag.className = "chatroom-message";
    MessageTag.innerText = "내용:"+message.content + "  시간:" + message.creationTime;
    return MessageTag;
}

function addParticipations (event) {
  console.log(event.target.innerHTML);
  message.participations.push({
    userId: event.target.innerHTML,
    conversationId: "",
    joinTime: "",
  });
  console.log(message);
}

/*  message = {
    ...message,
    participations: [...participations, {
      userId: event.target.innerHTML,
      conversationId: "",
      joinTime: "",
    }],
  }*/


/* 메신저 연결 끊기 호출 */
function disconnect() {
  if (stompClient !== null) {
    stompClient.disconnect();
  }
  console.log("Disconnected");
}


/* 메시지 보내기 */
const btn_message_send = document.querySelector("#btn-send");
btn_message_send.addEventListener("click", send);

function send() {
  const message_send = document.querySelector("#message-send");
  const chatroom_content = document.querySelector("#chatroom-content");
  stompClient.send("/chat/room/"+chatroom_content.dataset.roomNo, {}, JSON.stringify({
    type: "SEND",
    messageId: "",
    authorId: "",
    roomNo: chatroom_content.dataset.roomNo,
    content: message_send.value,
    creationTime: "",
  }));
  message_send.value = null;
  message_send.focus;
}


let message = {
  type: "NEWJOIN",
  messageId: "",
  authorId: "",
  roomNo: "",
  content: "",
  creationTime: "",
  participations: [],
}

/*
* {
    userId:"",
    conversationId:"",
    joinTime:"",
  }
* */