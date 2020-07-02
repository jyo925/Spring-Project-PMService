let state = {
  selectedChatRoom:"",
  countChatRoomList:1,
}

function count() {
  document.getElementsByClassName("badge badge-pill badge-success").innerHTML = state.countChatRoomList;
}
count();

const chatRoom_userList = document.querySelector("#chatroom-userlist");
const chatRoom_content = document.querySelector("#chatroom-content");

/* 메신저 연결 호출 -> WebSokcet 방식 */
/* 메신저 연결 호출 -> WebSokcet 방식 */
const btn_connect = document.querySelector("#messenger-connect");
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
        createChatRoomAfterInviting(data);
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
  data.ChatRooms.map(chatRoom => {
    console.log(chatRoom);
    const chat_list = document.querySelector("#chat-list");
    chat_list.append(chatRoomTag(chatRoom));
    chat_list.addEventListener("click", enterChatRoom);
    stompClient.subscribe("/topic/room/" + chatRoom.roomNo, function (response) {
      const message = JSON.parse(response.body);
      console.log(response);
      if(message.type == "SEND") {
        document.querySelector(`p[data-room-no="${chatRoom.roomNo}"]`).innerHTML = message.content;
        chatRoom_content.append(messageTag2(message));
      }
      if(message.type == "ENTER") {
        message.messageList.map(message => {
          chatRoom_content.append(messageTag2(message));
        });
        message.usersList.map(user => {
          chatRoom_userList.append(userListInChatRoomTag(user));
        });
      }
    });
  });
}

function chatRoomTag(chat) {
  const chatRoomElement = document.createElement("div");
  chatRoomElement.className = "mail-list";
  chatRoomElement.dataset.roomNo = chat.roomNo;
  chatRoomElement.innerHTML =
    `<div class="form-check">
     </div>
     <div class="content">
       <p class="sender-name"> 작성자: ${chat.authorId} 채팅방번호: ${chat.roomNo}</p>
       <p class="message_text" data-room-no="${chat.roomNo}">${chat.content} </p>
     </div>
     <div class="details">
       <i class="mdi mdi-star-outline"></i>
     </div>`;
  return chatRoomElement;
}


/* 기능 - 채팅 초대 목록 띄우기 */
/* 기능 - 채팅 초대 목록 띄우기 */
const btn_fetchUserList = document.querySelector('#btn-fetchUserList');
btn_fetchUserList.addEventListener("click", fetchUserList);

function fetchUserList() {
  stompClient.send("/chat/init/"+document.getElementById("user-id-hidden").value,{},JSON.stringify({
    type: "FETCHUSERLIST",
  }));
}

function fetchUserListTag(data) {
  data.UserList.map( user => {
    const user_list = document.querySelector("#user-list");
    let profile_list_item = document.createElement("li");
    profile_list_item.className = "profile-list-item";
    profile_list_item.innerHTML =
       `<a href="#">
        <span class="pro-pic"><img src="../../images/faces/face1.jpg" alt=""></span>
        <div class="user">
        <p class="u-name">${user.userId}</p>
        <p class="u-designation">${user.positionName}</p>
        </div>`
    profile_list_item.addEventListener("click", addParticipations);
    user_list.append(profile_list_item);
  });
}


/* 기능 - 대화 상대 초대하기 */
/* 기능 - 대화 상대 초대하기 */
const btn_invite = document.getElementById("btn-invite");
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

function createChatRoomAfterInviting(data) {
  console.log(data);
  while(chatRoom_content.hasChildNodes())
  { chatRoom_content.removeChild( chatRoom_content.firstChild ); }
  chatRoom_content.dataset.roomNo = data.roomNo;
  data.participations.map( user => {
    console.log(user);
    chatRoom_userList.innerHTML += user.userId + "유저목록";
  });
  stompClient.subscribe("/topic/room/"+ data.roomNo, function(response) {
    /* 받은 메시지 띄우기*/
    const message = JSON.parse(response.body);
    chatRoom_content.append(messageTag2(message));
  });
}


/* 채팅 내용 동적 태그 생성 함수 */
/* 채팅 내용 동적 태그 생성 함수 */
/* 채팅방 목록 -> 채팅방 ENTER - 메시지 태그 */
/* 채팅방 목록 -> 채팅방 ENTER - 메시지 태그 */
function messageTag2(message) {
  const MessageTag = document.createElement("div");
  MessageTag.className = "chatroom-message";
  MessageTag.innerHTML =
       `<img src="" alt="" id="chatroom-user-image">
        <p id="chatroom-user-id">${message.authorId}</p>
        <p id="chatroom-user-message">${message.content}</p>
        <p id="chatroom-user-message-time">${message.creationTime}</p>
        `;
  return MessageTag;
}


/* 채팅방 목록 -> 채팅방 입장 */
/* 채팅방 목록 -> 채팅방 입장 */
function enterChatRoom(event) {
  const mail_list = event.target.closest(".mail-list")
  /*document.querySelectorAll(".mail-list").map( element => {
    element.classList.remove("new_mail");
  })*/
  console.log(mail_list.dataset.roomNo);
  mail_list.classList.add("new_mail")
  chatRoom_content.dataset.roomNo = mail_list.dataset.roomNo;
  while(chatRoom_content.hasChildNodes())
  { chatRoom_content.removeChild( chatRoom_content.firstChild ); }
  while(chatRoom_userList.hasChildNodes())
  { chatRoom_userList.removeChild( chatRoom_userList.firstChild); }

  stompClient.send("/chat/room/"+chatRoom_content.dataset.roomNo, {}, JSON.stringify({
    type: "ENTER",
  }));
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
  stompClient.send("/chat/room/"+chatRoom_content.dataset.roomNo, {}, JSON.stringify({
    type: "SEND",
    messageId: "",
    authorId: "",
    roomNo: chatRoom_content.dataset.roomNo,
    content: message_send.value,
    creationTime: "",
  }));
  message_send.value = null;
  message_send.focus;
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
