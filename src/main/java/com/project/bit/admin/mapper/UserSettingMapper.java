package com.project.bit.admin.mapper;

import com.project.bit.admin.domain.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserSettingMapper {

    //사용자 목록
    public List<UserDTO> selectUser() ;

    //사용자 등록
    public void insertUser(UserDTO userDTO);

    //사용자 비밀번호 리셋
    public void resetUserPw(UserDTO userDTO);
}
