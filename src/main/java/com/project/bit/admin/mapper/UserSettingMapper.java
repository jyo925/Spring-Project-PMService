package com.project.bit.admin.mapper;

import com.project.bit.admin.domain.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserSettingMapper {

    //사용자 목록
    public List<UserVO> selectUser() ;

    //사용자 등록
    public void insertUser(UserVO userVO);

    //사용자 비밀번호 리셋
    public void resetUserPw(UserVO userVO);
}
