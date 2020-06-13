package com.project.bit.approval.service;

import com.project.bit.approval.domain.ApDTO;
import com.project.bit.approval.domain.ApPathDTO;
import com.project.bit.approval.domain.ApproverVO;
import com.project.bit.approval.mapper.ApMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public int postApprovers(List<ApproverVO> approvers, Long apDocNo) {
        int insertCount = 0;
        for(int i=0; i<approvers.size(); i++){
            ApDTO apDTO = ApDTO.builder()
                    .apDocNo(apDocNo)
                    .apStep(i+1)
                    .approver(approvers.get(i).getUserId())
                    .apDutyName(approvers.get(i).getDutyName()).build();

            apMapper.insertApprover(apDTO);
            if(apDTO.getApStep() == 1) apMapper.updateApReceiveDate(apDTO);
            insertCount++;
        }

        return insertCount;
    }

}
