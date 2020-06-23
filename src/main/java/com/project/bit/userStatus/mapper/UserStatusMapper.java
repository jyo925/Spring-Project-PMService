package com.project.bit.userStatus.mapper;


import com.project.bit.approval.domain.Criteria;
import com.project.bit.userStatus.domain.UserStatusVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserStatusMapper {

     //사용자현황 목록
     public List<UserStatusVO> selectUserStatus(Criteria cri);

     //Total Count
     int selectCountUserStatus();


}
