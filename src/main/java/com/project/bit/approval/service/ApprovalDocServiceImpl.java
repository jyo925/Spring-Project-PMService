package com.project.bit.approval.service;

import com.project.bit.approval.domain.ApFormDTO;
import com.project.bit.approval.mapper.ApDocMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApprovalDocServiceImpl implements ApprovalDocService{

    @Autowired
    ApDocMapper apDocMapper;

    @Override
    public ApFormDTO getApForm(String apFormNo) {
        return apDocMapper.selectApForm(apFormNo);
    }
}
