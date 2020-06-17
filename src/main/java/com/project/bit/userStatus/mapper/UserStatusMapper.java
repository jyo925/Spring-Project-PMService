package com.project.bit.userStatus.mapper;

import com.project.bit.userStatus.domain.Criteria;
import com.project.bit.userStatus.domain.UserStatusVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserStatusMapper {

     public List<UserStatusVO> selectUserStatus();

     //페이징처리
     public List<UserStatusVO> getListWithPaging(Criteria cri);


}
