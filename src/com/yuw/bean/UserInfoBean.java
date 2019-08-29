package com.yuw.bean;

import java.util.Date;

/**
 * 实体类映射要求：
 * 	  要求实体类的属性名和数据库的字段名，名字要一致；（可以不区分大小写，但是内容要一样）
 * @author Administrator
 *
 */
public class UserInfoBean {

	// 属性
	private Integer user_Id;
	private String user_Name;
	private String user_Psw;
	private Date user_Createtime;
	private Integer isdeleted;

	// 行为
	public Integer getUser_Id() {
		return user_Id;
	}

	public void setUser_Id(Integer user_Id) {
		this.user_Id = user_Id;
	}

	public String getUser_Name() {
		return user_Name;
	}

	public void setUser_Name(String user_Name) {
		this.user_Name = user_Name;
	}

	public String getUser_Psw() {
		return user_Psw;
	}

	public void setUser_Psw(String user_Psw) {
		this.user_Psw = user_Psw;
	}

	public Date getUser_Createtime() {
		return user_Createtime;
	}

	public void setUser_Createtime(Date user_Createtime) {
		this.user_Createtime = user_Createtime;
	}

	public Integer getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(Integer isdeleted) {
		this.isdeleted = isdeleted;
	}

	// 开发方便，提供toString方法

	@Override
	public String toString() {
		return "UserInfoBean [user_Id=" + user_Id + ", user_Name=" + user_Name + ", user_Psw=" + user_Psw
				+ ", user_Createtime=" + user_Createtime + ", isdeleted=" + isdeleted + "]";
	}

}
