package com.project.bit.admin.service;

import com.project.bit.admin.domain.TeamDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TeamSettingService {

    //부서리스트
    List<TeamDTO> teamSettingList();
    //부서등록
    void regitTeam(TeamDTO teamDTO);



    //사용자등록 셀렉박스
    public List<TeamDTO> getTeamForOption();


}
