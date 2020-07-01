package com.project.bit.admin.controller;

import com.project.bit.admin.domain.TeamDTO;
import com.project.bit.admin.service.TeamSettingService;
import com.project.bit.approval.domain.ApFileDTO;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        //log.info("여기요>>>>>>>>>>>>>>>>>"+teamSettingService.teamSettingList());

        return "admin/teamSettingList";
    }

    //부서 상세
    @GetMapping(value = "/getTeamDetail")
    @ResponseBody
    public ResponseEntity<TeamDTO>  getTeamDetail(int teamCode){

        TeamDTO teamDTO = teamSettingService.getTeamDetail(teamCode);

        return new ResponseEntity<>(teamDTO, HttpStatus.OK);
    }

    //부서 정보수정
    @PostMapping(value = "/updateTeam")
    public String modifyTeam(TeamDTO teamDTO){

        teamSettingService.modifyTeam(teamDTO);

        return "redirect:/admin/teamSettingList";
    }

    //부서등록
    @GetMapping(value = "/regitTeam")
    public String regitTeam(TeamDTO teamDTO){

        teamSettingService.regitTeam(teamDTO);

        log.info("등록이라네~!!!!!>>>>"+teamDTO);

        return "redirect:/admin/teamSettingList";
    }

    //부서삭제
    @PostMapping(value = "/deleteTeam")
    @ResponseBody
    public ResponseEntity<String> removeTeam(int teamCode){

        teamSettingService.removeTeam(teamCode);

        log.info("삭제삭제삭제삭제삭제삭제삭제삭제>>>>>"+teamCode);

        return new ResponseEntity<String>("delete", HttpStatus.OK);
    }


    //사용자등록 셀렉박스
    @RequestMapping(value="/getTeamForOption", method= RequestMethod.POST)
    public @ResponseBody List<TeamDTO> getTeamForOption(TeamDTO teamDTO) {


        log.info("셀렉박스 팀옵션>>>>>"+teamSettingService.getTeamForOption());

        return teamSettingService.getTeamForOption();
    }



}
