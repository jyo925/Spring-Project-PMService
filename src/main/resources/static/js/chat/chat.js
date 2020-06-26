
async function getData(url='') {
  const response = await fetch(url, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
    },
  });
  return response.json();
}

const testElem = document.getElementById("test");
testElem.addEventListener("click", connect);


let stompClient = null;
/*
  connect() method
  client.connect(headers, connectCallback);
  client.connect(headers, connectCallback, errorCallback);
  client.connect(headers, connectCallback, errorCallback, closeEventCallback);
 */

function connect() {
  let socket = new SockJS("/endpoint");
  stompClient = Stomp.over(socket);
  stompClient.connect({}, function (frame) {
    console.log("Connected" + frame);
    const dialogue = document.getElementById("chatTest");
    const ulElement = document.createElement('ul');
    ulElement.className = 'chat-list';
    dialogue.append(ulElement);
    getData("http://localhost:8080/chat/initial")
      .then( data => data.ChatRooms)
      .then( chatRooms => chatRooms.map( chat => {
          console.log(chat);
          const liElement = document.createElement('li');
          liElement.className = 'list';
          liElement.innerText = chat.content;
          ulElement.append(liElement);
          stompClient.subscribe("/topic/room/"+chat.conversationId, function (response) {
            console.log(response);
            dialogue.innerText = JSON.parse(response.body).content;
          });
        })
      );
  });
}

function disconnect() {
  if (stompClient !== null) {
    stompClient.disconnect();
  }
  console.log("Disconnected");
}

function sendMessage() {
  stompClient.send("/room/1", {}, JSON.stringify({}));
}

function send() {
  const messageElem = document.getElementById('sendMessage');
  const roomNo = document.getElementById('roomNo');
  stompClient.send("/topic/room/"+roomNo.value, {}, JSON.stringify({
    content: messageElem.value,
  }));
}

const messageBtnElem = document.getElementById('sendMessage btn');
messageBtnElem.addEventListener("click", send);

let message = {
  type: "",
  messageId: "",
  authorId: "",
  roomNo: "",
  content: "",
  creationTime: "",
  participations: [{
    userId:"",
    conversationId:"",
    joinTime:"",
  }],
}