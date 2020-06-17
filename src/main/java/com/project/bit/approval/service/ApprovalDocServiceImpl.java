package com.project.bit.approval.service;

import com.project.bit.approval.domain.*;
import com.project.bit.approval.mapper.ApDocMapper;
import com.project.bit.approval.mapper.ApFileMapper;
import com.project.bit.approval.mapper.ApReferrerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApprovalDocServiceImpl implements ApprovalDocService {

    @Autowired
    ApDocMapper apDocMapper;

    @Autowired
    ApFileMapper apFileMapper;

    @Autowired
    ApReferrerMapper apReferrerMapper;

    @Override
    public ApFormDTO getApForm(String apFormNo) {
        return apDocMapper.selectApForm(apFormNo);
    }

    @Override
    public int postApDoc(ApDocDTO apDocDTO) {
        return apDocMapper.insertApDoc(apDocDTO);
    }

    @Override
    public Long getNewApDocNo(ApDocDTO apDocDTO) {
        return apDocMapper.selectNewApDocNo(apDocDTO);
    }

    //진행문서함
    @Override
    public List<ApDocListVO> getApProgressList(String apDocWriter, Criteria cri) {
        return apDocMapper.selectApProgressList(apDocWriter, cri);
    }


    @Override
    public List<Integer> getApDocCount(String apDocWriter, Criteria cri) {

        List<Integer> apDocCountList = new ArrayList<Integer>();
        //결재진행(진행중, 반려) 개수
        apDocCountList.add(apDocMapper.selectCountApDoc(0, apDocWriter, cri) +
                apDocMapper.selectCountApDoc(2, apDocWriter, cri));
        //결재대기 개수
        apDocCountList.add(apDocMapper.selectCountApCheck(apDocWriter));
        //임시저장 개수
        apDocCountList.add(apDocMapper.selectCountApDoc(3, apDocWriter, cri));

        return apDocCountList;
    }

    //결재 대기 문서함
    @Override
    public List<ApDocListVO> getApCheckList(String apDocWriter, Criteria cri) {
        return apDocMapper.selectApCheckList(apDocWriter, cri);
    }

    @Override
    public int postApDocFiles(ApFileDTO apFileDTO) {
        long apDocNo = apFileDTO.getApDocNo();
        String[] apFileName = apFileDTO.getApFileName().split("[*]b[*]l[*]a[*]b[*]l[*]a[*],*");
        String[] apFilePath = apFileDTO.getApFilePath().split(",");
        String[] apFileUuid = apFileDTO.getApFileUuid().split(",");
        String[] apFileType = apFileDTO.getApFileType().split(",");

        for (int i = 0; i < apFileName.length; i++) {
            System.out.println(apFileName[i]);
            apFileMapper.insertApFile(ApFileDTO.builder()
                    .apDocNo(apDocNo)
                    .apFileName(apFileName[i])
                    .apFileUuid(apFileUuid[i])
                    .apFilePath(apFilePath[i])
                    .apFileType(apFileType[i]).build());
        }

        return 0;
    }

    @Override
    public List<ReferrerVO> getReferrerUserList() {
        return apReferrerMapper.selectReferrerUserList();
    }

    @Override
    public ApDocDTO getApDoc(String apDocNo) {

        return apDocMapper.selectApDoc(apDocNo);
    }

    @Override
    public List<String> getApDocViewableUsers(String apDocNo) {
        return apDocMapper.selectApDocViewableUsers(apDocNo);
    }
}
