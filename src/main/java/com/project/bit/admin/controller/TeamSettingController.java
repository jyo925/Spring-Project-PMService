package com.project.bit.admin.controller;

import com.project.bit.admin.domain.TeamDTO;
import com.project.bit.admin.service.TeamSettingService;
import com.project.bit.admin.service.TeamSettingServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Log
@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class TeamSettingController {

    private TeamSettingService teamSettingService;

    //부서 리스트
    @GetMapping(value = "/teamSettingList")
    public String teamSettingList(Model model){

        model.addAttribute("teamSettingList",teamSettingService.teamSettingList());
        log.info("여기요>>>>>>>>>>>>>>>>>"+teamSettingService.teamSettingList());

        return "admin/teamSettingList";
    }

    //부서등록
    @GetMapping(value = "/regitTeam")
    public String regitTeam(TeamDTO teamDTO){

        teamSettingService.regitTeam(teamDTO);

        return "redirect:/admin/regitTeam";
    }





    //사용자등록 셀렉박스
    @RequestMapping(value="/getTeamForOption", method= RequestMethod.POST)
    public @ResponseBody List<TeamDTO> getTeamForOption(TeamDTO teamDTO) {

        System.out.println("getTeamForOption");

        log.info(">>>>>"+teamSettingService.getTeamForOption());

        return teamSettingService.getTeamForOption();
    }



}
