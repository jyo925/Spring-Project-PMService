package com.project.bit.chat.mapper;

import com.project.bit.chat.domain.Participation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ParticipationMapper {

  List<Participation> findByUserId(String userId);
  int save(List<Participation> participations);

}
