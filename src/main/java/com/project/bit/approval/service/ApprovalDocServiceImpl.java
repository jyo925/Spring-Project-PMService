package com.project.bit.approval.service;

import com.project.bit.approval.domain.ApDocDTO;
import com.project.bit.approval.domain.ApDocListVO;
import com.project.bit.approval.domain.ApFormDTO;
import com.project.bit.approval.domain.Criteria;
import com.project.bit.approval.mapper.ApDocMapper;
import groovy.lang.IntRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApprovalDocServiceImpl implements ApprovalDocService{

    @Autowired
    ApDocMapper apDocMapper;

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
        apDocCountList.add(apDocMapper.selectCountApDoc(0, apDocWriter,cri)+
                apDocMapper.selectCountApDoc(2, apDocWriter,cri));
        //결재대기 개수
        apDocCountList.add(apDocMapper.selectCountApCheck(apDocWriter));
        //임시저장 개수
        apDocCountList.add(apDocMapper.selectCountApDoc(3, apDocWriter,cri));

        return apDocCountList;
    }

    //결재 대기 문서함
    @Override
    public List<ApDocListVO> getApCheckList(String apDocWriter, Criteria cri) {
        return apDocMapper.selectApCheckList(apDocWriter, cri);
    }
}
