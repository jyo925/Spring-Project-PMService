package com.project.bit.admin.mapper;

import com.project.bit.admin.domain.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserSettingMapper {

    public void resetUserPw(UserDTO userDTO);
}
