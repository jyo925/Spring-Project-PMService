package com.project.bit.project.domain;

import lombok.Data;

@Data
public class ProjectInfoVO extends ProjectDTO{
	private String userTeamName;
	private String userPositionName;
	private int projectTaskAccount;
	private int projectMemberAccount;
}
