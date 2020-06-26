package com.project.bit.admin.service;

import com.project.bit.admin.domain.TeamDTO;
import com.project.bit.admin.mapper.TeamSettingMapper;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log
@Service
@AllArgsConstructor
public class TeamSettingServiceImpl implements TeamSettingService{


    private TeamSettingMapper teamSettingMapper;

    @Override
    public List<TeamDTO> teamSettingList() {

        return teamSettingMapper.selectTeamList();
    }

    @Override
    public void regitTeam(TeamDTO teamDTO) {

        teamSettingMapper.insertTeam(teamDTO);
        log.info("부서 등록..............."+teamDTO);

    }

    @Override
    public List<TeamDTO> getTeamForOption() {

        return teamSettingMapper.selectTeamForOption();
    }
}
