package com.hibernate.entity;

import java.sql.Timestamp;

public class User_role {

	private String username, roleid;
	private Timestamp createtime;

	public User_role() {
		super();
	}

	public User_role(String username, String roleid, Timestamp createtime) {
		super();
		this.username = username;
		this.roleid = roleid;
		this.createtime = createtime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "User_role [username=" + username + ", roleid=" + roleid + ", createtime=" + createtime + "]";
	}

}
