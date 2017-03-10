package com.doorlock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doorlock.dao.IDemo;
import com.doorlock.entity.DemoDAO;

/**
 * Mybatis 映射服务实现
 * @author liyc
 * @date 2017年3月10日 下午1:59:09
 */
@Service
public class DemoServiceImpl implements IDemoService{
	@Autowired
	private IDemo idemo;

	@Override
	public void insertDemo(DemoDAO demo) {
		idemo.insertDemo(demo);
	}

	@Override
	public List<Integer> selectGroup() {
		return idemo.selectGroup();
	}
}
