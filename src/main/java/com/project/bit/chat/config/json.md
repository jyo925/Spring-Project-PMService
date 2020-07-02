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

### Chat Docs

##### 초대 유저 목록 마크업
```
<li class="list active">
  <div class="profile"><img src="" alt="image"><span class="online"></span></div>
  <div class="info">
    <p>Jeong 39</p>
    <p>Message Content</p>
  </div>
  <small class="text-muted my-auto">19 min</small>
</li>
<li class="list active">
  <div class="profile"><img src="" alt="image"><span class="online"></span></div>
  <div class="info">
    <p>User Id</p>
    <p>Message Content</p>
  </div>
  <small class="text-muted my-auto">19 min</small>
</li>
```

### 오라클
유저 시퀀스 조회
SELECT * FROM USER_SEQUENCES

