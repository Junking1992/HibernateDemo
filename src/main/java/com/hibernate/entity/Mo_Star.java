package com.hibernate.entity;

public class Mo_Star {
	public String uuid, ts, star, url;

	public Mo_Star(String uuid, String ts, String star, String url) {
		super();
		this.uuid = uuid;
		this.ts = ts;
		this.star = star;
		this.url = url;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getTs() {
		return ts;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Mo_Star [star=" + star + ", url=" + url + ", uuid=" + uuid + ", ts=" + ts + "]";
	}

}
