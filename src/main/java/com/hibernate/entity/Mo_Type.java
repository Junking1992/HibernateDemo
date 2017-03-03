package com.hibernate.entity;

import java.util.Date;

public class Mo_Type {
	public String uuid, type;

	public Date ts;

	public Mo_Type() {
		super();
	}

	public Mo_Type(String uuid, Date ts, String type) {
		super();
		this.uuid = uuid;
		this.ts = ts;
		this.type = type;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Date getTs() {
		return ts;
	}

	public void setTs(Date ts) {
		this.ts = ts;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Mo_Type [type=" + type + ", uuid=" + uuid + ", ts=" + ts + "]";
	}

}
