package com.project.bit.project.domain;

import lombok.Data;

@Data
public class ProjectMemberVO extends ProjectMemberDTO{
	private String teamName;
	private String positionName;
	private String dutyName;
}
