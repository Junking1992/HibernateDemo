package com.hibernate.entity;

import java.sql.Timestamp;

public class User_info {

	private String user_name, user_pass, user_id, real_name;

	private Timestamp last_logintime;

	public User_info() {
		super();
	}

	public User_info(String user_name, String user_pass, String user_id, String real_name, Timestamp last_logintime) {
		super();
		this.user_name = user_name;
		this.user_pass = user_pass;
		this.user_id = user_id;
		this.real_name = real_name;
		this.last_logintime = last_logintime;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_pass() {
		return user_pass;
	}

	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public Timestamp getLast_logintime() {
		return last_logintime;
	}

	public void setLast_logintime(Timestamp last_logintime) {
		this.last_logintime = last_logintime;
	}

	@Override
	public String toString() {
		return "User_info [user_name=" + user_name + ", user_pass=" + user_pass + ", user_id=" + user_id + ", real_name=" + real_name + ", last_logintime=" + last_logintime + "]";
	}

}
