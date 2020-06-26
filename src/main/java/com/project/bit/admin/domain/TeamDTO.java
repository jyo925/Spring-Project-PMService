package com.project.bit.admin.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class TeamDTO {
	
	private int teamCode;
	private String teamName;
	private int teamUpper;
	private int teamSeq;
	private String teamUse;
	private int level;

}
