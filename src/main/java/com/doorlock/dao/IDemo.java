package com.doorlock.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.doorlock.entity.DemoDAO;


/**
 * Mybatis 映射接口
 * 
 * @author liyc
 * @date 2017年3月10日 下午1:57:23
 */
@Repository
public interface IDemo {
	public void insertDemo(DemoDAO demo);

	public List<Integer> selectGroup();
}
