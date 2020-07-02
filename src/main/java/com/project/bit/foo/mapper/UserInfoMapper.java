package com.project.bit.foo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.project.bit.foo.domain.UserInfoVO;

@Mapper
public interface UserInfoMapper {
	
	@Select("SELECT COUNT(*) as projects  from projects p inner JOIN PROJECT_MEMBERS pm on p.project_code = pm.project_code where pm.user_id = #{userId}")
	UserInfoVO getProjects(String userId);
	
	@Select("select count(*) as tasks from PROJECT_TASKS pt inner join PROJECT_TASK_GROUPS ptg on pt.taskgroup_code = ptg.taskgroup_code inner join projects p on p.project_code = ptg.project_code inner join PROJECT_MEMBERS pm ON pm.project_code = ptg.project_code where pm.user_id = #{userId}")
	UserInfoVO getTasks(String userId);
	
	@Select("select count(*) as finished from projects p inner JOIN PROJECT_MEMBERS pm on p.project_code = pm.project_code where pm.user_id = #{userId} and p.project_statuscode='projstatus200'")
	UserInfoVO getFinished(String userId);

}
