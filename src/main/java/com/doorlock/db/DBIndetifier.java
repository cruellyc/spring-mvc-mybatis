package com.doorlock.db;

/**
 * 保存数据库标识。每个线程由独立的对象存储
 * 
 * @author liyc
 * @date 2017年3月10日 下午2:00:18
 */
public class DBIndetifier {
	private static ThreadLocal<String> dbKey = new ThreadLocal<String>();

	public static void setDBKey(final String dbKeyPara) {
		dbKey.set(dbKeyPara);
	}

	public static String getDBKey() {
		return dbKey.get();
	}
}
