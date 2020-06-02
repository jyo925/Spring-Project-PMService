package com.project.bit.project.domain;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Alias("ProjectVO")
@Getter @Setter @ToString
public class ProjectVO extends ProjectDTO {
	private int projectOutputAccount;
	private int projectIssueAccount;
}
