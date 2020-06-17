package com.project.bit.approval.service;

import com.project.bit.approval.domain.ApDTO;
import com.project.bit.approval.domain.ApPathDTO;
import com.project.bit.approval.domain.ApproverVO;
import com.project.bit.approval.mapper.ApMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Log
public class ApprovalServiceImpl implements ApprovalService {

    @Autowired
    ApMapper apMapper;

    @Override
    public List<ApproverVO> getApproverList(String apFormNo, String userId) {

        ApPathDTO path = apMapper.selectApPath(apFormNo);
        List<ApproverVO> approvers = new ArrayList<ApproverVO>();

        List<String> dutyName = new ArrayList<>(Arrays.asList("developer", "manager", "PM", "PMO"));
        String userDutyName = apMapper.selectApproverDutyName(userId); //pm인경우 manager가 나오면 안됨

        for(ApproverVO approver : apMapper.selectApproverList(path, userId)){
            //결재선에 자기가 결재자로 들어가면 삭제
            if(approver.getUserId().equals(userId)){
                log.info(approver.getUserId() + " == " + userId);
                continue;
            }
            if(dutyName.indexOf(userDutyName)>dutyName.indexOf(approver.getDutyName())){
                continue;
            }
            approvers.add(approver);
        }

        //결재선이 텅 비었다면?...바로 윗 직급 불러와서 결재선 셋팅
        if(approvers.size() == 0){
            approvers.add(apMapper.selectSuperiorApprover(userId));
            log.info("추가된 상위 결재자 정보: "+ apMapper.selectSuperiorApprover(userId));
        }

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
