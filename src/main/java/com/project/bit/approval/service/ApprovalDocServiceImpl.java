package com.project.bit.approval.service;

import com.project.bit.approval.domain.ApDocDTO;
import com.project.bit.approval.domain.ApDocListVO;
import com.project.bit.approval.domain.ApFormDTO;
import com.project.bit.approval.domain.Criteria;
import com.project.bit.approval.mapper.ApDocMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Long getApDocNo(ApDocDTO apDocDTO) {
        return apDocMapper.selectApDocNo(apDocDTO);
    }

    //진행문서함
    @Override
    public List<ApDocListVO> getApProgressList(String apDocWriter, Criteria cri) {
        return apDocMapper.selectApProgressList(apDocWriter, cri);
    }
}
