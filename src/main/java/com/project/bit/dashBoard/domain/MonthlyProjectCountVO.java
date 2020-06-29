package com.project.bit.dashBoard.domain;

import lombok.Data;

@Data
public class MonthlyProjectCountVO {
	private String projectMonth;
	private int projectCount;
	private int devType100;
	private int devType200;
	private int devType300;
	private int devType400;
	private int devType500;
}