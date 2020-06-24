package com.project.bit.admin.service;

import com.project.bit.admin.domain.TeamDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TeamSettingService {

    public List<TeamDTO> getTeamForOption();

}
