package com.doorlock.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doorlock.db.DBTask;
import com.doorlock.db.DBTaskMgr;
import com.doorlock.entity.DemoDAO;
import com.doorlock.service.IDemoService;


/**
 *
 * @author liyc
 * @date 2016年9月20日 下午2:58:35
 */
@Controller
@RequestMapping("DBTest")
public class DBTestCtl {
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private IDemoService demoServiceImpl;
	
	@ResponseBody
	@RequestMapping("test")
	public void test(@RequestParam(value = "callback", required = false) String callback) {
		
		DemoDAO demo = new DemoDAO();
		demo.setA(6);
		demo.setB("12121212");
		demo.setC(100);
		DBTask taskInsert1 = new DBTask("test1", demoServiceImpl, "insertDemo", demo);
		DBTask taskInsert2 = new DBTask("test2", demoServiceImpl, "insertDemo", demo);
		DBTask taskInsert3 = new DBTask("test3", demoServiceImpl, "insertDemo", demo);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		List<DBTask> taskInsertList = new ArrayList<DBTask>();
		taskInsertList.add(taskInsert1);
		taskInsertList.add(taskInsert2);
		taskInsertList.add(taskInsert3);
		logger.info("开始插入数据:" + format.format(new Date()));
		for (DBTask task : taskInsertList) {
			DBTaskMgr.instance().excute(task);
		}
		while (true) {
			int success = 0;
			for (DBTask task : taskInsertList) {
				if (!task.isFinish()) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					++success;
				}
			}

			if (success == taskInsertList.size()) {
				break;
			}
		}

		logger.info("插入数据结束:" + format.format(new Date()));
	}
}
