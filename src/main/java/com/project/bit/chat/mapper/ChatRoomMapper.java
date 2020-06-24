package com.project.bit.chat.mapper;

import com.project.bit.chat.domain.ChatRoom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatRoomMapper {

  int save(ChatRoom chatRoom);

}
