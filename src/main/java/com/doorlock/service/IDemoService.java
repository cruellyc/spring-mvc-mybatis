package com.doorlock.service;

import java.util.List;

import com.doorlock.entity.DemoDAO;

/**
 * Mybatis 映射服务接口
 * @author  liyc
 * @date 2017年3月10日 下午1:58:27
*/
public interface IDemoService {
	public void insertDemo(DemoDAO demo);
	public List<Integer> selectGroup();
}
