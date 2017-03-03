package com.jun.all;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class Crawler implements Runnable{
	static final Logger logger = LoggerFactory.getLogger(Crawler.class);
	
	public final static String TITLE = "标题";
	public final static String CODE = "识别码";
	public final static String URL = "链接";
	public final static String DATE = "发行时间";
	public final static String LENGTH = "长度";
	public final static String DIRECTOR = "导演";
	public final static String MAKER = "制作商";
	public final static String PUBLISHER = "发行商";
	public final static String SERIAL = "系列";
	public final static String TYPE = "类别";
	public final static String STAR = "演员";
	public final static String OTHER = "其他";
	
	private PreparedStatement dataStatement;
	private PreparedStatement tagListStatement;
	private PreparedStatement starListStatement;
	private PreparedStatement queryStatement;
	public Connection conn;
	public int sleepTime = 200;
	public String name;

	public Map<String, String> data = null;
	public List<String> tagList = null;
	public List<Map<String, String>> starList = null;
	
	private ComboPooledDataSource comboPooledDataSource;
	public HtmlOut urlOut;

	public boolean justDoIt() {
		String html = urlOut.getHtml();
		if (!"".equals(html.trim())) {
			try {
				conn = comboPooledDataSource.getConnection();
				conn.setAutoCommit(false);
				getInfo(html);
				queryStatement = conn.prepareStatement("select mo_uuid from MO_INFO_T where mo_url=?");
				queryStatement.setString(1, urlOut.getUrlStr());
				ResultSet rs = queryStatement.executeQuery();
				if (rs.next()) {
					rs.close();
					conn.rollback();
					conn.close();
					return false;
				} else {
					rs.close();
				}
				dataStatement = conn.prepareStatement("insert into MO_INFO_T (MO_UUID,MO_TITLE,MO_CODE,MO_URL,MO_DATE,MO_LENGTH,MO_DIRECTOR,MO_MAKER,MO_PUBLISHER,MO_SERIAL,MO_TYPE,MO_STAR,MO_OTHER) values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
				tagListStatement = conn.prepareStatement("insert into MO_TYPE_T (MO_UUID,MO_TYPE) values (?,?)");
				starListStatement = conn.prepareStatement("insert into MO_STAR_T (MO_UUID,MO_STAR,MO_URL) values (?,?,?)");
				save();
				conn.commit();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if(conn != null){
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return false;
	}

	private String getDataValue(String key) {
		String value = data.get(key) == null ? "" : data.get(key);
		data.remove(key);
		return value;
	}

	private String getOther() {
		Set<String> otherKey = null;
		StringBuffer sb = new StringBuffer("");
		if (data.size() > 0) {
			otherKey = data.keySet();
			for (String key : otherKey) {
				sb.append(key + ":" + data.get(key) + "|");
			}
		}
		String returnStr = sb.toString();
		if (returnStr.length() > 260) {
			returnStr = returnStr.substring(0, 260) + "......";
		}
		return returnStr;
	}

	public void getInfo(String html) {
		data = new HashMap<String, String>();
		tagList = new ArrayList<String>();
		starList = new ArrayList<Map<String, String>>();
		Document doc = Jsoup.parse(html);
		Element msgDiv = doc.select("div.col-md-3.info").first();
		Elements ps = msgDiv.select("p");

		String title = doc.select("a.bigImage").first().attr("title");
		data.put(TITLE, title);
		data.put(URL, urlOut.getUrlStr());
		for (int i = 0; i < ps.size(); i++) {
			String p = ps.get(i).text();
			if (p.indexOf(":") > -1 && !"类别:".equals(p)) {// 有:无类别的
				String[] arr = p.split(":");
				if (arr.length > 1) {// 两个String
					data.put(arr[0].trim(), arr[1].trim());
				} else {// 一个String
					if (i < (ps.size() - 1)) {// 有下文
						String p2 = ps.get(i + 1).text();
						if (p2.indexOf(":") > -1) {// 下文有:
							data.put(arr[0].trim(), "");
						} else {// 无:
							data.put(arr[0].trim(), p2);
						}
						i++;
					} else {// 无下文
						data.put(arr[0].trim(), "");
					}
				}
			} else if (p.indexOf(":") > -1 && "类别:".equals(p)) {// 有:有类别的
				if (i < (ps.size() - 1)) {// 有下文
					Element e = ps.get(i + 1);
					Elements spans = e.select("span");
					if (spans.size() > 0) {
						for (Element span : spans) {
							tagList.add(span.text().trim());
						}
					}
					i++;
				}
			} else {
				data.put("其他" + i, p);
			}
		}

		data.put(TYPE, tagList.size() + "");

		Elements stars = doc.select("a.avatar-box");
		data.put(STAR, stars.size() + "");
		Map<String, String> starMap = null;
		for (Element star : stars) {
			starMap = new HashMap<String, String>();
			starMap.put("URL", star.attr("href"));
			starMap.put("NAME", star.text());
			starList.add(starMap);
		}

	}

	private void save() throws SQLException {
		String uuid = UUID.randomUUID().toString();
		dataStatement.setString(1, uuid);
		dataStatement.setString(2, getDataValue(TITLE));
		dataStatement.setString(3, getDataValue(CODE));
		dataStatement.setString(4, getDataValue(URL));
		dataStatement.setString(5, getDataValue(DATE));
		dataStatement.setString(6, getDataValue(LENGTH));
		dataStatement.setString(7, getDataValue(DIRECTOR));
		dataStatement.setString(8, getDataValue(MAKER));
		dataStatement.setString(9, getDataValue(PUBLISHER));
		dataStatement.setString(10, getDataValue(SERIAL));
		dataStatement.setString(11, getDataValue(TYPE));
		dataStatement.setString(12, getDataValue(STAR));
		dataStatement.setString(13, getOther());
		dataStatement.executeUpdate();

		for (String tag : tagList) {
			tagListStatement.setString(1, uuid);
			tagListStatement.setString(2, tag);
			tagListStatement.executeUpdate();
		}

		for (Map<String, String> star : starList) {
			starListStatement.setString(1, uuid);
			starListStatement.setString(2, star.get("NAME"));
			starListStatement.setString(3, star.get("URL"));
			starListStatement.executeUpdate();
		}
	}
	
	public void setComboPooledDataSource(ComboPooledDataSource comboPooledDataSource) {
		this.comboPooledDataSource = comboPooledDataSource;
	}

	public void setUrlOut(HtmlOut urlOut) {
		this.urlOut = urlOut;
	}
	
	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		while(true){
			if(justDoIt()){
				logger.info(name + "-insert:" + urlOut.urlStr);
				System.out.println(name + "-insert:" + urlOut.urlStr);
			}
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
