package com.project.bit.admin.service;

import com.project.bit.admin.domain.OutputMonthlyCountVO;
import com.project.bit.admin.domain.OutputStatusCountVO;

import java.util.List;

public interface AdminMainService {

    public List<OutputStatusCountVO> getOutputAllStatus();
    public List<OutputMonthlyCountVO> getOutputMonthly();
}
