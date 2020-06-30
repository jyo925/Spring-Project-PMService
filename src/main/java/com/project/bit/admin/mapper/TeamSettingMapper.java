package com.project.bit.admin.mapper;

import com.project.bit.admin.domain.TeamDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TeamSettingMapper {

    //부서리스트
    public List<TeamDTO> selectTeamList();

    //부서 상세정보
    public TeamDTO selectTeamDetail(int teamCode);

    //부서 상세정보 상위부서 리스트
    List<Map<String, Object>> selectTeamUpperList(int depth, int teamCode);

    //부서 정보수정
    public int updateTeam(TeamDTO teamDTO);

    //부서등록
    public void insertTeam(TeamDTO teamDTO);

    //부서 삭제
    public int deleteTeam(int teamCode);

    //사용자 등록페이지 셀렉박스
    public List<TeamDTO> selectTeamForOption();

}
