package com.jun.all;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.je.LockMode;

public class MyBerkeleyDB {
	private Environment environment; // 环境
	private Database database; // 数据库
	private String charset; // 编码
	private String path; // 路径
	private long chacheSize; // 缓冲区大小

	public MyBerkeleyDB() {
		charset = "utf-8"; // 编码默认使用UTF-8
		chacheSize = 10000; // 缓冲区大小默认为10000
	}

	// 设置编码
	public void setCharset(String charset) {
		this.charset = charset;
	}

	// 设置路径
	public void setPath(String path) {
		// 判断Path是否存在
		File file = new File(path);
		if (file.mkdir()) {
			//System.out.println(path + "已创建！"); // 不存在则创建一个
		} else {
			//System.out.println(path + "已存在！"); // 存在则说明已存在
		}

		// 确定存储路径
		this.path = path;
	}

	// 设置缓冲区大小
	public boolean setChacheSize(long size) {
		if (size <= 0 || size >= 1000000000) {
			return false; // 使用默认的大小
		}
		this.chacheSize = size;
		return true;
	}

	// 同时设置路径和缓冲区,创建环境
	public void setEnvironment(String path, long chacheSize) {
		setPath(path);
		setChacheSize(chacheSize);
		// 配置环境
		EnvironmentConfig envConfig = new EnvironmentConfig();
		envConfig.setAllowCreate(true);
		envConfig.setCacheSize(this.chacheSize);
		// 创建环境
		environment = new Environment(new File(this.path), envConfig);
	}

	// 打开名字是dbName的数据库
	public void open(String dbName) {
		DatabaseConfig dbConfig = new DatabaseConfig();
		dbConfig.setAllowCreate(true);
		dbConfig.setSortedDuplicates(false); // 不存储重复关键字
		this.database = environment.openDatabase(null, dbName, dbConfig);
	}

	// 关闭
	public void close() {
		database.close();
		environment.close();
	}

	// 存储
	public void put(Object key, Object value) {
		DatabaseEntry k = new DatabaseEntry(toByteArray(key));
		DatabaseEntry v = new DatabaseEntry(toByteArray(value));
		database.put(null, k, v);
	}

	// 取值
	public Object get(Object key) {
		DatabaseEntry k = new DatabaseEntry(toByteArray(key));
		DatabaseEntry v = new DatabaseEntry();

		database.get(null, k, v, LockMode.DEFAULT);
		if (v == null) {
			return null;
		}
		return toObject(v.getData());
	}

	// 按照键值删除数据
	public Object del(Object key) {
		DatabaseEntry k = new DatabaseEntry(toByteArray(key)); // 键值转化
		Object value = get(key); // 获取值
		database.removeSequence(null, k); // 删除值
		return value; // 返回删除的值
	}

	// 获取数据库存储数据的大小
	public long size() {
		return database.count();
	}
	
	// 序列化对象
	private static byte[] toByteArray(Object obj) {
		if (obj == null)
			return null;
		byte[] bytes = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			oos.flush();
			bytes = bos.toByteArray();
			oos.close();
			bos.close();
		} catch (IOException ex) {
			// ex.printStackTrace();
		}
		return bytes;
	}

	// 反序列化
	private static Object toObject(byte[] bytes) {
		if (bytes == null)
			return null;
		Object obj = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bis);
			obj = ois.readObject();
			ois.close();
			bis.close();
		} catch (IOException ex) {
			// ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			// ex.printStackTrace();
		}
		return obj;
	}
}
