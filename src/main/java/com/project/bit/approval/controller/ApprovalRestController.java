package com.project.bit.approval.controller;

import com.project.bit.approval.domain.ApproverVO;
import com.project.bit.approval.domain.ReferrerVO;
import com.project.bit.approval.service.ApprovalDocService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("approval")
@Log
public class ApprovalRestController {

    @Autowired
    ApprovalDocService apDocService;
    
    //지우기
    @PostMapping("/getReferrerUserList")
    public List<ReferrerVO> getReferrerUserList() {
        return apDocService.getReferrerUserList();
    }
}
