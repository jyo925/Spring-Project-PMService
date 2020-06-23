package com.project.bit.approval.controller;

import com.project.bit.approval.domain.ApFileDTO;
import com.project.bit.approval.domain.ApproverVO;
import com.project.bit.approval.domain.ReferrerVO;
import com.project.bit.approval.service.ApprovalDocService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("approval")
@Log
public class ApprovalRestController {

    @Autowired
    ApprovalDocService apDocService;
    
    //지우기
    @GetMapping(value = "/getApprovalFiles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ApFileDTO>> getApprovalFiles(String apDocNo) {
        log.info("fileRestControleererererereddddddddddddddddddddddddddddddddddddddddddddddddd");
        log.info(apDocService.getApFiles(apDocNo)+"");
        return new ResponseEntity<>(apDocService.getApFiles(apDocNo), HttpStatus.OK);
    }
}
