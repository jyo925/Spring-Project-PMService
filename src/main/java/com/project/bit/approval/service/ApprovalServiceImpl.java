package com.project.bit.approval.service;

import com.project.bit.approval.domain.ApPathDTO;
import com.project.bit.approval.domain.ApproverVO;
import com.project.bit.approval.mapper.ApMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApprovalServiceImpl implements ApprovalService {

    @Autowired
    ApMapper apMapper;

    @Override
    public List<ApproverVO> getApproverList(String apFormNo, String userId) {

        ApPathDTO path = apMapper.selectApPath(apFormNo);
        List<ApproverVO> approvers = apMapper.selectApproverList(path, userId);

        return approvers;
    }

}
