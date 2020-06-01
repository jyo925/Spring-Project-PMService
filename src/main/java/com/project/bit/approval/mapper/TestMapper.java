package com.project.bit.approval.mapper;

import com.project.bit.approval.domain.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TestMapper {

//    @Select("select * from test_user")
    List<UserVO> selectUser();

    UserVO selectForm(String userId);

}
