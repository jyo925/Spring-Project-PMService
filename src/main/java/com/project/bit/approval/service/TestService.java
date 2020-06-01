package com.project.bit.approval.service;

import com.project.bit.approval.domain.UserVO;

import java.util.List;

public interface TestService {

    List<UserVO> selectUser();

    UserVO selectForm(String userId);
}
