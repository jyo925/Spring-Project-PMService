package com.project.bit.admin.service;

import com.project.bit.admin.domain.TeamDTO;
import com.project.bit.admin.mapper.TeamSettingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamSettingServiceImpl implements TeamSettingService{

    @Autowired
    private TeamSettingMapper teamSettingMapper;

    @Override
    public List<TeamDTO> getTeamForOption() {

        return teamSettingMapper.selectTeamForOption();
    }
}
