package com.project.bit.userStatus.mapper;

import java.util.List;

import com.project.bit.approval.domain.Criteria;
import org.apache.ibatis.annotations.Mapper;

import com.project.bit.userStatus.domain.UserStatusVO;

@Mapper
public interface UserStatusMapper {

     //사용자현황 목록
     public List<UserStatusVO> selectUserStatus(Criteria cri);

     //Total Count
     int selectCountUserStatus();


}
