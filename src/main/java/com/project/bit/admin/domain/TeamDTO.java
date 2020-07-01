package com.project.bit.admin.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter @Setter
@ToString
public class TeamDTO {
	
	private int teamCode;
	private String teamName;
	private int teamUpper;
	private List<Map<String,Object>> teamUpperList;
	private int teamSeq;
	private int level;

}
