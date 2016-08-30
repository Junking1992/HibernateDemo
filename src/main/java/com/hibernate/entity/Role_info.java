package com.hibernate.entity;

import java.sql.Timestamp;

public class Role_info {

	private String roleid, rolename;
	private Timestamp createtime;

	public Role_info() {
		super();
	}

	public Role_info(String roleid, String rolename, Timestamp createtime) {
		super();
		this.roleid = roleid;
		this.rolename = rolename;
		this.createtime = createtime;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "Role_info [roleid=" + roleid + ", rolename=" + rolename + ", createtime=" + createtime + "]";
	}

}
