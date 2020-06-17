
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
    getData("http://localhost:8080/chat/initial")
      .then( data => data.ChatRooms)
      .then( chatRooms => chatRooms.map( chat => {
          console.log(chat);
          dialogue.innerText += chat.content;
          stompClient.subscribe("/topic/room/"+chat.conversationId, function (response) {
            console.log(response)
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

