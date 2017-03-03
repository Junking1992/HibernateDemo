package com.crawl;

import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
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

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class TestBDB {
	
	private ComboPooledDataSource comboPooledDataSource;
	private PreparedStatement dataStatement;
	private PreparedStatement tagListStatement;
	private PreparedStatement starListStatement;
	private PreparedStatement queryStatement;
	public Connection conn;
	public int findCount = 0;
	public int insertCount = 0;

	Map<String, String> data = null;
	List<String> tagList = null;
	List<Map<String, String>> starList = null;
	

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

//	public void read() throws UnsupportedEncodingException {
//		File envHome = new File("E:/mo_b/frontier");
//		if (!envHome.exists()) {
//			envHome.mkdir();
//		}
//		EnvironmentConfig envConfig = new EnvironmentConfig();
//		envConfig.setAllowCreate(true);
//		envConfig.setTransactional(true);
//		envConfig.setLocking(true);
//		DatabaseConfig dbConfig = new DatabaseConfig();
//		dbConfig.setAllowCreate(true);
//		dbConfig.setTransactional(true);
//		dbConfig.setDeferredWrite(false);
//		Environment env = new Environment(envHome, envConfig);
//		System.out.println(env.getDatabaseNames());
//		Transaction txn = env.beginTransaction(null, null);
//		Database urlsDB = env.openDatabase(txn, "Statistics", dbConfig);
//		txn.commit();
//		Cursor cursor = urlsDB.openCursor(null, null);
//		DatabaseEntry foundKey = new DatabaseEntry();
//		DatabaseEntry foundValue = new DatabaseEntry();
//		String theKey = null;
//		String theValue = null;
//		// while (cursor.getNext(foundKey, foundValue, LockMode.DEFAULT) ==
//		// OperationStatus.SUCCESS) {
//		// theKey = new String(foundKey.getData(), "UTF-8");
//		// theValue = new String(foundValue.getData(), "UTF-8");
//		// count++;
//		// }
//		System.out.println(urlsDB.count());
//		cursor.close();
//		urlsDB.close();
//		env.close();
//	}

	public MyBerkeleyDB db = null;

	public TestBDB() throws PropertyVetoException {
		comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setDriverClass("oracle.jdbc.driver.OracleDriver");
		comboPooledDataSource.setJdbcUrl("jdbc:oracle:thin:@127.0.0.1:1521/myorcl");
		comboPooledDataSource.setUser("jun");
		comboPooledDataSource.setPassword("junking");
		
		db = new MyBerkeleyDB();
		db.setEnvironment("E:/MyBDB", 100000);
		db.open("BDB1");
	}

	public String getUrl() {
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
	
	public String getHtmlByUrl(String http){
		StringBuffer sb = new StringBuffer("");
		try {
            URL url = new URL(http);
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
        }
		return sb.toString();
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
		if(returnStr.length() > 260){
			returnStr = returnStr.substring(0, 260)+"......";
		}
		return returnStr;
	}

	public void getInfo(String html) {
		tagList = new ArrayList<String>();
		starList = new ArrayList<Map<String, String>>();
		Document doc = Jsoup.parse(html);
		Element msgDiv = doc.select("div.col-md-3.info").first();
		Elements ps = msgDiv.select("p");

		String title = doc.select("a.bigImage").first().attr("title");
		data.put(TITLE, title);
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
		dataStatement.setString(1,uuid);
		dataStatement.setString(2,getDataValue(TITLE));
		dataStatement.setString(3,getDataValue(CODE));
		dataStatement.setString(4,getDataValue(URL));
		dataStatement.setString(5,getDataValue(DATE));
		dataStatement.setString(6,getDataValue(LENGTH));
		dataStatement.setString(7,getDataValue(DIRECTOR));
		dataStatement.setString(8,getDataValue(MAKER));
		dataStatement.setString(9,getDataValue(PUBLISHER));
		dataStatement.setString(10,getDataValue(SERIAL));
		dataStatement.setString(11,getDataValue(TYPE));
		dataStatement.setString(12,getDataValue(STAR));
		dataStatement.setString(13,getOther());
		dataStatement.executeUpdate();
		
		for(String tag : tagList){
			tagListStatement.setString(1, uuid);
			tagListStatement.setString(2, tag);
			tagListStatement.executeUpdate();
		}
		
		for(Map<String,String> star : starList){
			starListStatement.setString(1, uuid);
			starListStatement.setString(2, star.get("NAME"));
			starListStatement.setString(3, star.get("URL"));
			starListStatement.executeUpdate();
		}
	}
	
	public void go(String url, String html) throws SQLException{
		conn = comboPooledDataSource.getConnection();
		conn.setAutoCommit(false);
		queryStatement = conn.prepareStatement("select mo_uuid from MO_INFO_T where mo_url=?");
		queryStatement.setString(1, url);
		ResultSet rs = queryStatement.executeQuery();
		if(rs.next()){
			rs.close();
			conn.close();
			return;
		}else{
			rs.close();
		}
		dataStatement = conn.prepareStatement("insert into MO_INFO_T (MO_UUID,MO_TITLE,MO_CODE,MO_URL,MO_DATE,MO_LENGTH,MO_DIRECTOR,MO_MAKER,MO_PUBLISHER,MO_SERIAL,MO_TYPE,MO_STAR,MO_OTHER) values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
		tagListStatement = conn.prepareStatement("insert into MO_TYPE_T (MO_UUID,MO_TYPE) values (?,?)");
		starListStatement = conn.prepareStatement("insert into MO_STAR_T (MO_UUID,MO_STAR,MO_URL) values (?,?,?)");
		data = new HashMap<String, String>();
		data.put(URL, url);
		
		// 解析html
		getInfo(html);
		//写入数据库
		save();
		conn.commit();
		conn.close();
	}

	public static void main(String[] args) throws Exception {
		TestBDB bdb = new TestBDB();
//		String url = null;
//		while((url = bdb.getUrl()) != null){
//			System.out.println("find:" + url + " count:" + (++bdb.findCount));
//			String html = bdb.getHtmlByUrl("https://avmo.pw/cn/movie/" + url);
//			if("".equals(html.trim())){
//				continue;
//			}
//			bdb.go("https://avmo.pw/cn/movie/"+url, html);
//			System.out.println("insert:" + url + " count:" + (++bdb.insertCount));
//			Thread.sleep(200);
//		}
//		System.out.println(bdb.getUrl());
//		bdb.db.put("A", 121);
//		bdb.db.put("B", 119);
//		bdb.db.put("C", 106);
//		bdb.db.del("A");
//		bdb.db.del("B");
//		bdb.db.del("C");
//		bdb.db.del("D");
		System.out.println((char) ((int)bdb.db.get("A")));
		System.out.println((char) ((int)bdb.db.get("B")));
		System.out.println((char) ((int)bdb.db.get("C")));
		System.out.println((char) ((int)bdb.db.get("D")));
//		
//		System.out.println((char) 121);
//		System.out.println((char) 119);
//		System.out.println((char) 106);
//		bdb.db.close();
	}

}
