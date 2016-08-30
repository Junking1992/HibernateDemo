package com.hibernate.entity;

import java.sql.Timestamp;

public class Permission_info {

	private String permissionid, permissionname;
	private Timestamp createtime;

	public Permission_info() {
		super();
	}

	public Permission_info(String permissionid, String permissionname, Timestamp createtime) {
		super();
		this.permissionid = permissionid;
		this.permissionname = permissionname;
		this.createtime = createtime;
	}

	public String getPermissionid() {
		return permissionid;
	}

	public void setPermissionid(String permissionid) {
		this.permissionid = permissionid;
	}

	public String getPermissionname() {
		return permissionname;
	}

	public void setPermissionname(String permissionname) {
		this.permissionname = permissionname;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "Permission_info [permissionid=" + permissionid + ", permissionname=" + permissionname + ", createtime=" + createtime + "]";
	}

}
