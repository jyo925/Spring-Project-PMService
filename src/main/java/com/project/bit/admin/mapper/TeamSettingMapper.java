package com.project.bit.admin.mapper;

import com.project.bit.admin.domain.TeamDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeamSettingMapper {

    //부서리스트
    public List<TeamDTO> selectTeamList();
    //부서등록
    public void insertTeam(TeamDTO teamDTO);

    //사용자 등록페이지 셀렉박스
    public List<TeamDTO> selectTeamForOption();
}
