package com.project.bit.admin.service;

import com.project.bit.admin.domain.OutputStatusCountVO;
import com.project.bit.admin.mapper.AdminMainMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminMainServiceImpl implements AdminMainService{

    private AdminMainMapper adminMainMapper;


    @Override
    public List<OutputStatusCountVO> getOutputAllStatus() {

        return adminMainMapper.selectOutputAllStatus();
    }
}
