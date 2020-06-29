package com.project.bit.foo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.project.bit.foo.domain.projectView.ProjectsMembersVO;

@Mapper
public interface AuthorityMapper {
	
	@Select("SELECT pm.USER_ID as USER_ID, pm.DUTY_CODE as DUTY_CODE, u.user_name as user_name, u.USER_EMAIL as USER_EMAIL, p.POSITION_NAME as POSITION_NAME, u.USER_PHOTO as USER_PHOTO FROM PROJECT_MEMBERS pm INNER JOIN USERS u ON pm.USER_ID = u.USER_ID INNER JOIN POSITIONS p ON p.POSITION_CODE = u.POSITION_CODE WHERE pm.project_code = #{projectCode}")
	List<ProjectsMembersVO> selectProjectMembers(String projectCode);
	
}
