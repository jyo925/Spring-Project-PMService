package com.project.bit.admin.controller;

import com.project.bit.admin.domain.TeamDTO;
import com.project.bit.admin.service.TeamSettingService;
import com.project.bit.admin.service.TeamSettingServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Log
@Controller
@ResponseBody
@AllArgsConstructor
public class TeamSettingController {

    private TeamSettingService teamSettingService;

    @RequestMapping(value="/getTeamForOption", method= RequestMethod.POST)
    public @ResponseBody List<TeamDTO> getTeamForOption(TeamDTO teamDTO) {

        System.out.println("getTeamForOption");

        log.info(">>>>>"+teamSettingService.getTeamForOption());

        return teamSettingService.getTeamForOption();
    }



}
