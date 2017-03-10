package com.doorlock.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源。可根据不同的数据索引连接不同的数据库
 * 
 * @author liyc
 * @date 2017年3月10日 下午2:01:28
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	@Override
	public Object determineCurrentLookupKey() {
		return DBIndetifier.getDBKey();
	}
}
