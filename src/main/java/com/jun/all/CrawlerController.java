package com.jun.all;

import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;

import com.crawl.MyBerkeleyDB;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class CrawlerController {

	public int crawlerCount = 5;

	public ComboPooledDataSource comboPooledDataSource;

	public HtmlOut urlOut;

	public MyBerkeleyDB db;

	public void init() {
		try {
			comboPooledDataSource = new ComboPooledDataSource();
			comboPooledDataSource.setDriverClass("oracle.jdbc.driver.OracleDriver");
			comboPooledDataSource.setJdbcUrl("jdbc:oracle:thin:@127.0.0.1:1521/myorcl");
			comboPooledDataSource.setUser("jun");
			comboPooledDataSource.setPassword("junking");

			urlOut = new HtmlOut();
			db = new MyBerkeleyDB();
			db.setEnvironment("E:/MyBDB", 100000);
			db.open("BDB1");
			urlOut.setDb(db);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		CrawlerController crawlerController = new CrawlerController();
		crawlerController.init();
		List<Thread> tList = new ArrayList<Thread>();
		for (int i = 0; i < crawlerController.crawlerCount; i++) {
			Crawler crawler = new Crawler();
			crawler.setComboPooledDataSource(crawlerController.comboPooledDataSource);
			crawler.setUrlOut(crawlerController.urlOut);
			Thread t = new Thread(crawler);
			crawler.setName("No:" + i);
			t.setName("No:" + i);
			tList.add(t);
			t.start();
		}
		while(true){
			for(int i = 0; i < tList.size(); i++){
				Thread t = tList.get(i);
				if(!t.isAlive()){
					System.out.println(t.getName()+"线程挂了...");
					Crawler crawler = new Crawler();
					crawler.setName(t.getName());
					Thread rt = new Thread(crawler);
					rt.setName(t.getName());
					tList.remove(i);
					tList.add(i, rt);
					rt.start();
				}
			}
		}
	}

}
