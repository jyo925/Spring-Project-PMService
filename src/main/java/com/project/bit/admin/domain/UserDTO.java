package com.project.bit.admin.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.sql.Date;

@Data

public class UserDTO {

	private String userId;
	private String userPw;
	private String userName;
	private int positionCode;
	private int teamCode;
	private String userEmail;
	private String userPhone;
	private Date userRegitDate;

}
