package com.hibernate.entity;

import java.sql.Timestamp;

public class Role_mp {

	private String roleid, moduleid, permissionid;
	private Timestamp createtime;

	public Role_mp() {
		super();
	}

	public Role_mp(String roleid, String moduleid, String permissionid, Timestamp createtime) {
		super();
		this.roleid = roleid;
		this.moduleid = moduleid;
		this.permissionid = permissionid;
		this.createtime = createtime;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getModuleid() {
		return moduleid;
	}

	public void setModuleid(String moduleid) {
		this.moduleid = moduleid;
	}

	public String getPermissionid() {
		return permissionid;
	}

	public void setPermissionid(String permissionid) {
		this.permissionid = permissionid;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "Role_mp [roleid=" + roleid + ", moduleid=" + moduleid + ", permissionid=" + permissionid + ", createtime=" + createtime + "]";
	}

}
