package com.project.bit.admin.controller;

import com.project.bit.admin.domain.OutputMonthlyCountVO;
import com.project.bit.admin.domain.OutputStatusCountVO;
import com.project.bit.admin.service.AdminMainService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Log
@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminMainController {

    private AdminMainService adminMainService;

    @GetMapping("/main")
    public String adminMain(Model model){

        return "admin/main";

    }

    @GetMapping("/main/outputAllStatus")
    @ResponseBody
    public List<OutputStatusCountVO> getOutputAllStatus(){

        return adminMainService.getOutputAllStatus();
    }

    @GetMapping("/main/outputMonthlyStatus")
    @ResponseBody
    public List<OutputMonthlyCountVO> getOutputMonthly(){


        return adminMainService.getOutputMonthly();
    }



}
