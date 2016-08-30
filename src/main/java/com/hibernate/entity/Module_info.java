package com.hibernate.entity;

import java.sql.Timestamp;

public class Module_info {
	
	private String moduleid, modulename;
	private Timestamp createtime;

	public Module_info() {
		super();
	}

	public Module_info(String moduleid, String modulename, Timestamp createtime) {
		super();
		this.moduleid = moduleid;
		this.modulename = modulename;
		this.createtime = createtime;
	}

	public String getModuleid() {
		return moduleid;
	}

	public void setModuleid(String moduleid) {
		this.moduleid = moduleid;
	}

	public String getModulename() {
		return modulename;
	}

	public void setModulename(String modulename) {
		this.modulename = modulename;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "Module_info [moduleid=" + moduleid + ", modulename=" + modulename + ", createtime=" + createtime + "]";
	}

}
