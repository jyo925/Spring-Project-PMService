package com.project.bit.chat.mapper;

import com.project.bit.chat.domain.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {

  List<Message> findAll();
  List<Message> findLastMessageByUserId(String userId);

}
