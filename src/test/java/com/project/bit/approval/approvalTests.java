package com.project.bit.approval;

import com.project.bit.approval.domain.ApPathDTO;
import com.project.bit.approval.domain.ApproverVO;
import com.project.bit.approval.mapper.ApDocMapper;
import com.project.bit.approval.mapper.ApMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class approvalTests {

    @Autowired
    ApMapper apMapper;

    @Autowired
    ApDocMapper apDocMapper;

    @Test
    public void selectApprovers(){
        ApPathDTO d = apMapper.selectApPath("1");
        List<ApproverVO> list = apMapper.selectApproverList(d, "user007");
        System.out.println(apMapper.selectApproverList(d, "user007"));
    }



    //결재선 불러오기
    public void selectApPath(){

        ApPathDTO d = apMapper.selectApPath("2");
        System.out.println(d.getApPath1());
        System.out.println(d.getApPath2());
        System.out.println(d.getApPath3());

    }
}
