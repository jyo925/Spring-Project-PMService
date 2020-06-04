package com.project.bit.project.domain;

import lombok.Data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Data
public class ProjectVO extends ProjectDTO {
	private int projectOutputAccount;
	private int projectIssueAccount;
}
