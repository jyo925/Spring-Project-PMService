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



/* 메신저 연결 호출 -> WebSokcet 방식 */
/* 메신저 연결 호출 -> WebSokcet 방식 */
const btn_connect = document.querySelector(".messenger-connect");
btn_connect.addEventListener("click", connect);
let stompClient = null;

function connect() {
  let socket = new SockJS("/endpoint");
  stompClient = Stomp.over(socket);

  stompClient.connect({}, function (frame) {
    console.log("Connected" + frame);
    stompClient.subscribe("/topic/init/" + document.getElementById("user-id-hidden").value, function (response) {
      const data = JSON.parse(response.body);
      if(data.ChatRooms) {
        fetchChatRoomListTag(data);
      }
      if(data.UserList) {
        fetchUserListTag(data);
      }
      if(data.type == "INVITE") {
        createChatRoomAfterInvited(data);
      }
    });
    setTimeout(stompClient.send("/chat/init/" + document.getElementById("user-id-hidden").value, {}, JSON.stringify({
      type: "FETCHCHATROOMLIST",
    })),1000);
  });
}

/* 메신저 연결 끊기 호출 */
/* 메신저 연결 끊기 호출 */
function disconnect() {
  if (stompClient !== null) {
    stompClient.disconnect();
  }
  console.log("Disconnected");
}


/* 채팅방 목록 불러오기 웹소켓*/
/* 채팅방 목록 불러오기 웹소켓*/
function fetchChatRoomListTag(data) {
  data.ChatRooms.map(chat => {
    console.log(chat);
    const chat_list = document.querySelector(".chat-list");
    chat_list.append(UserListTag(chat));
    chat_list.addEventListener("click", enterChatRoom);
    stompClient.subscribe("/topic/room/" + chat.roomNo, function (response) {
      const message = JSON.parse(response.body);
      document.querySelector(`p[data-room-no="${chat.roomNo}"]`).innerHTML = message.content;
      console.log(response);
    });
  });
}

function UserListTag(chat) {
  const userListTag = document.createElement("li");
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


/* 기능 - 채팅 초대 목록 띄우기 */
/* 기능 - 채팅 초대 목록 띄우기 */
const btn_fetchUserList = document.getElementById('fetchUserList');
btn_fetchUserList.addEventListener("click", fetchUserList);

function fetchUserList() {
  stompClient.send("/chat/init/"+document.getElementById("user-id-hidden").value,{},JSON.stringify({
    type: "FETCHUSERLIST",
  }));
}

function fetchUserListTag(data) {
  data.UserList.map( user => {
    const user_list = document.querySelector("#user-list");
    let userListDiv = document.createElement("li");
    userListDiv.className = "userList";
    userListDiv.innerHTML = user.userId;
    userListDiv.addEventListener("click", addParticipations);
    user_list.append(userListDiv);
  });
}

/* 기능 - 대화 상대 초대하기 */
/* 기능 - 대화 상대 초대하기 */
const btn_invite = document.getElementById("invite");
btn_invite.addEventListener("click", invite);

function addParticipations (event) {
  console.log(event.target.innerHTML);
  message.type = "INVITE"
  message.participations.push({
    userId: event.target.innerHTML,
    conversationId: "",
    joinTime: "",
  });
  console.log(message);
}

function invite() {
  stompClient.send("/chat/init/"+document.getElementById("user-id-hidden").value,{},JSON.stringify(message));
}

function createChatRoomAfterInvited(data) {
  const chatroom_userList = document.querySelector("#chatroom-userlist");
  const chatroom_content = document.querySelector("#chatroom-content");
  console.log(data);
  chatroom_content.dataset.roomNo = data.roomNo;
  data.participations.map( user => {
    console.log(user);
    chatroom_userList.innerHTML += user.userId + "유저목록";
  });
  stompClient.subscribe("/topic/room/"+ data.roomNo, function(response) {
    /* 받은 메시지 띄우기*/
    const Message = JSON.parse(response.body);
    const chatroom_content = document.querySelector("#chatroom-content");
    chatroom_content.append(MessageTag2(Message));
  });
}


/* 채팅방 목록 -> 채팅방 입장 */
/* 채팅방 목록 -> 채팅방 입장 */
function enterChatRoom(event) {
  console.log(event.target.closest('li').dataset.roomNo);
  const chatroom_content = document.getElementById("chatroom-content");
  const chatroom_userList = document.getElementById("chatroom-userlist");
  chatroom_content.dataset.roomNo = event.target.closest('li').dataset.roomNo;
  stompClient.subscribe("/topic/room/" + event.target.closest('li').dataset.roomNo, function (response) {
    const message = JSON.parse(response.body);
    console.log("메시지 리스트 : " + message.messageList);
    if (message.messageList) {
      message.messageList.map(message => {
        chatroom_content.append(messageTag(message));
      });
    }
    if (message.usersList) {
      message.usersList.map(user => {
        chatroom_userList.append(userListInChatRoomTag(user));
      });
    }
    if (message) {
      chatroom_content.append(messageTag(message));
    }
  });
  stompClient.send("/chat/room/"+event.target.closest('li').dataset.roomNo, {}, JSON.stringify({
    type: "ENTER",
  }));
}


/* 채팅방 목록 -> 채팅방 ENTER - 메시지 태그 */
/* 채팅방 목록 -> 채팅방 ENTER - 메시지 태그 */
function messageTag(message) {
  const message_tag = document.createElement("div");
  message_tag.id = "message-id";
  message_tag.innerHTML =
      `<p id="message-author-id">${message.authorId}</p>
   <p id="message-content">${message.content}</p>
   <p id="message-creationTime">${message.creationTime}</p>`
  return message_tag;
}

/* 채팅방 목록 -> 채팅방 ENTER - 대화 중인 유저 목록 동적 태그 */
/* 채팅방 목록 -> 채팅방 ENTER - 대화 중인 유저 목록 동적 태그 */
function userListInChatRoomTag(user) {
  const userTag = document.createElement("h6");
  userTag.innerHTML = user.userId;
  return userTag;
}

/* 채팅방 목록 -> 채팅방 ENTER - 메시지 보내기 */
/* 채팅방 목록 -> 채팅방 ENTER - 메시지 보내기 */
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


/* 채팅 내용 동적 태그 생성 함수 */
/* 채팅 내용 동적 태그 생성 함수 */
function MessageTag2(message) {
  const MessageTag = document.createElement("li");
  MessageTag.className = "chatroom-message";
  MessageTag.innerHTML =
      `<li class="chatroom-message">
        <img src="" alt="" id="chatroom-user-image">
        <p id="chatroom-user-id">${message.authorId}</p>
        <p id="chatroom-user-message">${message.content}</p>
        <p id="chatroom-user-message-time">${message.creationTime}</p>
    </li>`;
  return MessageTag;
}


let message = {
  type: "",
  messageId: "",
  authorId: "",
  roomNo: "",
  content: "",
  creationTime: "",
  participations: [],
}
