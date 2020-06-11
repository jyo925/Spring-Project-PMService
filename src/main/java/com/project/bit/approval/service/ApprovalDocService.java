package com.project.bit.approval.service;

import com.project.bit.approval.domain.ApDocDTO;
import com.project.bit.approval.domain.ApDocListVO;
import com.project.bit.approval.domain.ApFormDTO;

import java.util.List;

public interface ApprovalDocService {

    public ApFormDTO getApForm(String apFormNo);

    public int postApDoc(ApDocDTO apDocDTO);

    public List<ApDocListVO> getApProgressList(String apDocWriter);

}
