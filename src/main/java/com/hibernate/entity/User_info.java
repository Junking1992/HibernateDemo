package com.hibernate.entity;

import java.sql.Timestamp;

public class User_info {

	private String username, password, name;
	private Integer state;
	private Timestamp lastlogintime, createtime;

	public User_info() {
		super();
	}

	public User_info(String username, String password, String name, Integer state, Timestamp lastlogintime, Timestamp createtime) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.state = state;
		this.lastlogintime = lastlogintime;
		this.createtime = createtime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Timestamp getLastlogintime() {
		return lastlogintime;
	}

	public void setLastlogintime(Timestamp lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "User_info [username=" + username + ", password=" + password + ", name=" + name + ", state=" + state + ", lastlogintime=" + lastlogintime + ", createtime=" + createtime + "]";
	}

}
