package com.project.bit.admin.service;

import com.project.bit.admin.domain.TeamDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface TeamSettingService {

    //부서리스트
    List<TeamDTO> teamSettingList();

    //부서 상세정보
    TeamDTO getTeamDetail(int teamCode);

    //부서 정보수정
    boolean modifyTeam(TeamDTO teamDTO);

    //부서등록
    void regitTeam(TeamDTO teamDTO);

    //부서 삭제
    boolean removeTeam(int teamCode);


    //사용자등록 셀렉박스
    public List<TeamDTO> getTeamForOption();


}
