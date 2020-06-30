package com.project.bit.admin.service;

import com.project.bit.admin.domain.TeamDTO;
import com.project.bit.admin.mapper.TeamSettingMapper;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Log
@Service
@AllArgsConstructor
public class TeamSettingServiceImpl implements TeamSettingService{


    private TeamSettingMapper teamSettingMapper;

    @Override
    public List<TeamDTO> teamSettingList() {

        return teamSettingMapper.selectTeamList();
    }

    //부서 상세정보
    @Override
    public TeamDTO getTeamDetail(int teamCode) {

        TeamDTO dto = teamSettingMapper.selectTeamDetail(teamCode);
        dto.setTeamUpperList(teamSettingMapper.selectTeamUpperList(2,dto.getTeamCode()));

        log.info("여기보세요>>>>>>>>>>>>>>>>>>>>"+dto);
        return dto;
    }

    //부서 정보수정
    @Override
    public boolean modifyTeam(TeamDTO teamDTO) {

        return teamSettingMapper.updateTeam(teamDTO)==1;
    }


    @Override
    public void regitTeam(TeamDTO teamDTO) {

        teamSettingMapper.insertTeam(teamDTO);
        log.info("부서 등록..............."+teamDTO);

    }

    @Override
    public boolean removeTeam(int teamCode) {

        return teamSettingMapper.deleteTeam(teamCode)==1;
    }

    @Override
    public List<TeamDTO> getTeamForOption() {

        return teamSettingMapper.selectTeamForOption();
    }
}
