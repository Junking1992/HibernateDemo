package com.hibernate.entity;

public class Mo_Info {

	public String uuid, ts, title, code, url, date, length, director, maker, publisher, serial, type, star, other;

	public Mo_Info(String uuid, String ts, String title, String code, String url, String date, String length, String director, String maker, String publisher, String serial, String type, String star, String other) {
		super();
		this.uuid = uuid;
		this.ts = ts;
		this.title = title;
		this.code = code;
		this.url = url;
		this.date = date;
		this.length = length;
		this.director = director;
		this.maker = maker;
		this.publisher = publisher;
		this.serial = serial;
		this.type = type;
		this.star = star;
		this.other = other;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	@Override
	public String toString() {
		return "Mo_Info [code=" + code + ", uuid=" + uuid + ", ts=" + ts + ", title=" + title + ", url=" + url + ", date=" + date + ", length=" + length + ", director=" + director + ", maker=" + maker + ", publisher=" + publisher + ", serial=" + serial + ", type=" + type + ", star=" + star + ", other=" + other + "]";
	}

}
