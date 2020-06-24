package com.project.bit.userstatus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.bit.approval.domain.Criteria;
import com.project.bit.userstatus.domain.UserStatusVO;

@Mapper
public interface UserStatusMapper {

     public List<UserStatusVO> selectUserStatus();

     //페이징처리
     public List<UserStatusVO> getListWithPaging(Criteria cri);


}
