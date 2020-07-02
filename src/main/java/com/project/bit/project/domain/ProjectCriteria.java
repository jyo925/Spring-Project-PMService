package com.project.bit.project.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProjectCriteria {
	private int pageNum;
	private int amount;
	
	public ProjectCriteria() {
		this(1,10);
	}
	
	public ProjectCriteria(int pageNum, int amount) {
		this.amount = amount;
		this.pageNum = pageNum;
	}
}
