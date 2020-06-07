package com.project.bit.approval.service;

import com.project.bit.approval.domain.ApDocDTO;
import com.project.bit.approval.domain.ApFormDTO;

public interface ApprovalDocService {

    public ApFormDTO getApForm(String apFormNo);

    public int postApDoc(ApDocDTO apDocDTO);

}
