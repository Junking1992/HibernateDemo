package com.jun.all;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crawl.MyBerkeleyDB;

public class HtmlOut {
	static final Logger logger = LoggerFactory.getLogger(HtmlOut.class);
	
	public MyBerkeleyDB db = null;
	
	public String urlStr;
	
	public void setDb(MyBerkeleyDB db) {
		this.db = db;
	}
	
	public synchronized String getHtml(){
		StringBuffer sb = new StringBuffer("");
		try {
			urlStr = "https://avmo.pw/cn/movie/" + getUrl();
			logger.info("all at :" + urlStr);
//			System.out.println("all at :" + urlStr);
            URL url = new URL(urlStr);
            InputStream in =url.openStream();
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader bufr = new BufferedReader(isr);
            String buffer;
            while ((buffer = bufr.readLine()) != null) {
            	sb.append(buffer + "\r\n");
            }
            bufr.close();
            isr.close();
            in.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
		return sb.toString();
	}

	public synchronized String getUrl() {
		Object a = db.get("A");
		Object b = db.get("B");
		Object c = db.get("C");
		Object d = db.get("D");
		if (a == null && b == null && c == null && d == null) {
			db.put("A", 48);
			return "0";
		} else if (a != null && b == null && c == null && d == null) {
			int aa = (int) a;
			if (aa == 122) {
				db.put("A", 48);
				db.put("B", 48);
				return "00";
			} else {
				aa = addOne(aa);
				db.put("A", aa);
				return String.valueOf((char) aa);
			}
		} else if (a != null && b != null && c == null && d == null) {
			int aa = (int) a;
			int bb = (int) b;
			if (aa == 122 && bb == 122) {
				db.put("A", 48);
				db.put("B", 48);
				db.put("C", 48);
				return "000";
			} else if (aa == 122 && bb < 122) {
				db.put("A", 48);
				bb = addOne(bb);
				db.put("B", bb);
				return "0" + String.valueOf((char) bb);
			} else {
				aa = addOne(aa);
				db.put("A", aa);
				return String.valueOf((char) aa) + String.valueOf((char) bb);
			}
		} else if (a != null && b != null && c != null && d == null) {
			int aa = (int) a;
			int bb = (int) b;
			int cc = (int) c;
			if (aa == 122 && bb == 122 && cc == 122) {
				db.put("A", 48);
				db.put("B", 48);
				db.put("C", 48);
				db.put("D", 48);
				return "0000";
			} else if (aa == 122 && bb == 122 && cc < 122) {
				db.put("A", 48);
				db.put("B", 48);
				cc = addOne(cc);
				db.put("C", cc);
				return "0" + "0" + String.valueOf((char) cc);
			} else if (aa == 122 && bb < 122) {
				db.put("A", 48);
				bb = addOne(bb);
				db.put("B", bb);
				return "0" + String.valueOf((char) bb) + String.valueOf((char) cc);
			} else {
				aa = addOne(aa);
				db.put("A", aa);
				return String.valueOf((char) aa) + String.valueOf((char) bb) + String.valueOf((char) cc);
			}
		} else if (a != null && b != null && c != null && d != null) {
			int aa = (int) a;
			int bb = (int) b;
			int cc = (int) c;
			int dd = (int) d;
			if (aa == 122 && bb == 122 && cc == 122 && dd == 122) {
				db.put("A", 48);
				db.del("B");
				db.del("C");
				db.del("D");
				return null;
			} else if (aa == 122 && bb == 122 && cc == 122 && dd < 122) {
				db.put("A", 48);
				db.put("B", 48);
				db.put("C", 48);
				dd = addOne(dd);
				db.put("D", dd);
				return "0" + "0" + "0" + String.valueOf((char) dd);
			} else if (aa == 122 && bb == 122 && cc < 122) {
				db.put("A", 48);
				db.put("B", 48);
				cc = addOne(cc);
				db.put("C", cc);
				return "0" + "0" + String.valueOf((char) cc) + String.valueOf((char) dd);
			} else if (aa == 122 && bb < 122){
				db.put("A", 48);
				bb = addOne(bb);
				db.put("B", bb);
				return "0" + String.valueOf((char) bb) + String.valueOf((char) cc) + String.valueOf((char) dd);
			} else {
				aa = addOne(aa);
				db.put("A", aa);
				return String.valueOf((char) aa) + String.valueOf((char) bb) + String.valueOf((char) cc) + String.valueOf((char) dd);
			}
		}else{
			return null;
		}
	}
	
	public int addOne(int i) {
		if (i >= 48 && i < 57)
			return ++i;
		if (i == 57)
			return 97;
		if (i >= 97 && i < 122)
			return ++i;
		if (i == 122)
			return 48;
		return 48;
	}
	
	public String getUrlStr(){
		return this.urlStr;
	}
}
