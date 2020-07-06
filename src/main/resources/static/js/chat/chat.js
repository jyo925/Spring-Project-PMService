const chatRoom_userList = document.querySelector("#chatroom-userlist"),
      chatRoom_content = document.querySelector("#chatroom-content");
      currentLogginedUserId = document.getElementById("user-id-hidden").value;
let subscriptions = [];


/* search */
/*const options = {
  keys: [
      "userId",
  ],
};
let list = [];
let result ="";
let fuse;
document.querySelector("#finder").addEventListener("keyup", userSearch);
function userSearch() {
  fuse = new Fuse(list, options);
  let pattern = document.querySelector("#finder").value;
  console.log(pattern);
  result = fuse.search(pattern);
  console.log(result);
}*/

/* 메신저 연결 호출 -> WebSokcet 방식 */
/* 메신저 연결 호출 -> WebSokcet 방식 */
const btn_connect = document.querySelector("#messenger-connect");
btn_connect.addEventListener("click", connect);
let stompClient = null;

function connect() {
  btn_connect.disabled = true;
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
      if(data.type == "ENTER") {
        data.messageList.map(message => {
          chatRoom_content.append(messageTag2(message));
        });
        data.usersList.map(user => {
          chatRoom_userList.append(userListInChatRoomTag(user));
        });
      }
    });
  });
}

const btn = document.querySelector("#listup");
btn.addEventListener("click", listUp);

function listUp() {
  btn.removeEventListener("click", listUp);
  stompClient.send("/chat/init/" + document.getElementById("user-id-hidden").value, {}, JSON.stringify({
    type: "FETCHCHATROOMLIST",
  }));
  console.log(subscriptions);
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
  data.ChatRooms.forEach(chatRoom => {
    console.log(chatRoom);
    const chat_list = document.querySelector("#chat-list");
    chat_list.append(chatRoomTag(chatRoom));
    chat_list.addEventListener("click", enterChatRoom);
    let subscription = stompClient.subscribe("/topic/room/" + chatRoom.roomNo, function (response) {
        const message = JSON.parse(response.body);
        console.log(response);
        document.querySelector(`p[data-room-no="${chatRoom.roomNo}"]`).innerHTML = message.content;
        if(message.type == "SEND" && message.roomNo == chatRoom_content.dataset.roomNo ) {
          chatRoom_content.append(messageTag2(message));
        }
        if(message.yn == "Y") {
          console.log("YN");
          message.participations.forEach(user => {
          chatRoom_userList.append(userListInChatRoomTag(user));
          });
        }
      });
    subscription.roomNo = chatRoom.roomNo;
    subscriptions.push(subscription);
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
       <p class="sender-name">채팅방번호 ${chat.roomNo}</p>
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
  btn_fetchUserList.removeEventListener("click", fetchUserList);
  stompClient.send("/chat/init/"+document.getElementById("user-id-hidden").value,{},JSON.stringify({
    type: "FETCHUSERLIST",
  }));
}

function fetchUserListTag(data) {
  list = data.UserList;
  data.UserList.forEach( (user,index) => {
    const user_list = document.querySelector("#user-list");
    let profile_list_item = document.createElement("li");
    profile_list_item.className = "profile-list-item";
    profile_list_item.dataset.key = index;
    profile_list_item.innerHTML =
       `<a href="#">
        <span class="pro-pic"><img src="" alt=""></span>
        <div class="user">
        <p class="u-name"><h5 style="color: black;">${user.userId}</h5></p>
        <p class="u-name">${user.userName}</p>
        <p class="u-designation">${user.positionName}</p>
        </div>`
    profile_list_item.addEventListener("click", addParticipations);
    user_list.append(profile_list_item);
  });
  console.log(list);
}


/* 기능 - 대화 상대 초대하기 */
/* 기능 - 대화 상대 초대하기 */
const btn_invite = document.getElementById("btn-invite");
btn_invite.addEventListener("click", invite);

function addParticipations (event) {
  if(inviteMessage.participations.find( participation => participation.userId == event.target.innerHTML)) {
    inviteMessage.participations = inviteMessage.participations.filter( participation => participation.userId != event.target.innerHTML);
    console.log("중복값 잇어");
    event.target.closest('li').removeAttribute('style');
    return;
  }
  event.target.closest('li').style="background-color: aliceblue";
  ;
  console.log(event.target.innerHTML);
  inviteMessage.type = "INVITE"
  inviteMessage.participations.push({
    userId: event.target.innerHTML,
    conversationId: "",
    joinTime: "",
  });
  console.log(inviteMessage);
}

function invite() {
  stompClient.send("/chat/init/"+document.getElementById("user-id-hidden").value,{},JSON.stringify(inviteMessage));
  inviteMessage.participations=[];
  inviteMessage.type="";
  document.querySelectorAll(".profile-list-item").forEach( item => item.removeAttribute('style'));
}

document.querySelector("#btn-add").addEventListener("click", oldInvite);
function oldInvite() {
  inviteMessage.type="OLD";
  inviteMessage.roomNo=document.querySelector("#chatroom-content").dataset.roomNo;
  stompClient.send("/chat/init/"+document.getElementById("user-id-hidden").value,{},JSON.stringify(inviteMessage));
  inviteMessage.participations=[];
  inviteMessage.type="";
  document.querySelectorAll(".profile-list-item").forEach( item => item.removeAttribute('style'));
}

function createChatRoomAfterInviting(data) {
  console.log(data);
  while(chatRoom_userList.hasChildNodes())
  { chatRoom_userList.removeChild( chatRoom_userList.firstChild ); }
  while(chatRoom_content.hasChildNodes())
  { chatRoom_content.removeChild( chatRoom_content.firstChild ); }
  chatRoom_content.dataset.roomNo = data.roomNo;
  chatRoom_content.append(messageTag2(data))
  data.participations.forEach( user => {
    console.log(user);
    chatRoom_userList.append(userListInChatRoomTag(user));
  });
  document.getElementsByClassName('badge badge-pill badge-success')[0].innerText++;
}

  /*let subscription = stompClient.subscribe("/topic/room/"+ data.roomNo, function(response) {
    /!* 받은 메시지 띄우기*!/
    const message = JSON.parse(response.body);
    chatRoom_content.append(messageTag2(message));
  });
  subscription.roomNo = data.roomNo;
  subscriptions.push(subscription);*/

/* 채팅 내용 동적 태그 생성 함수 */
/* 채팅 내용 동적 태그 생성 함수 */
/* 채팅방 목록 -> 채팅방 ENTER - 메시지 태그 */
/* 채팅방 목록 -> 채팅방 ENTER - 메시지 태그 */
function messageTag2(message) {
  const rowTag = document.createElement("div");
  rowTag.className = message.authorId == currentLogginedUserId ? "row" :"row right";
  rowTag.innerHTML =
       `
        <div class="chatroom-message">
          <img src="" alt="" id="chatroom-user-image">
          <p id="chatroom-user-id">${message.authorId}</p>
          <p id="chatroom-user-message">${message.content}</p>
          <p id="chatroom-user-message-time">${moment(message.creationTime).format("ddd, h:mmA")}</p>
        </div>
        `;
  return rowTag;
}


/* 채팅방 목록 -> 채팅방 입장 */
/* 채팅방 목록 -> 채팅방 입장 */
function enterChatRoom(event) {
  const mail_list = event.target.closest(".mail-list");
  document.querySelectorAll(".mail-list").forEach( element =>
  element.classList.remove("new_mail"));
  console.log(mail_list.dataset.roomNo);
  mail_list.classList.add("new_mail")
  chatRoom_content.dataset.roomNo = mail_list.dataset.roomNo;
  while(chatRoom_content.hasChildNodes())
  { chatRoom_content.removeChild( chatRoom_content.firstChild ); }
  while(chatRoom_userList.hasChildNodes())
  { chatRoom_userList.removeChild( chatRoom_userList.firstChild); }

  stompClient.send("/chat/init/" + currentLogginedUserId, {}, JSON.stringify({
    type: "ENTER",
    roomNo: mail_list.dataset.roomNo,
  }));
}


/* 채팅방 목록 -> 채팅방 ENTER - 대화 중인 유저 목록 동적 태그 */
/* 채팅방 목록 -> 채팅방 ENTER - 대화 중인 유저 목록 동적 태그 */
function userListInChatRoomTag(user) {
  const userTag = document.createElement("h3");
  userTag.innerHTML = user.userId;
  return userTag;
}


/* 채팅방 목록 -> 채팅방 ENTER - 메시지 보내기 */
/* 채팅방 목록 -> 채팅방 ENTER - 메시지 보내기 */
const btn_message_send = document.querySelector("#btn-send");
btn_message_send.addEventListener("click", send);
document.querySelector('#message-send').addEventListener("keydown", function(e){
  if(e.keyCode == 13) {
    send();
  }
})

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

/* 채팅방 목록 -> 채팅방 ENTER - 채팅방 LEAVE */
/* 채팅방 목록 -> 채팅방 ENTER - 채팅방 LEAVE */

const btn_leave = document.querySelector("#btn-leave");
      btn_leave.addEventListener("click", leave);

function leave() {
  subscriptions.find(subscription => subscription.roomNo == chatRoom_content.dataset.roomNo )
      .unsubscribe();
  const test = document.querySelector(`.mail-list[data-room-no="${chatRoom_content.dataset.roomNo}"]`);
  console.log(test);
  test.remove();
  while(chatRoom_content.hasChildNodes())
  { chatRoom_content.removeChild( chatRoom_content.firstChild ); }
  while(chatRoom_userList.hasChildNodes())
  { chatRoom_userList.removeChild( chatRoom_userList.firstChild); }
  stompClient.send("/chat/room/"+chatRoom_content.dataset.roomNo, {}, JSON.stringify({
    type: "LEAVE",
    roomNo: chatRoom_content.dataset.roomNo,
  }));
  document.getElementsByClassName('badge badge-pill badge-success')[0].innerText--;
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

let inviteMessage = {
  type: "",
  participations: [],
  roomNo: "",
}