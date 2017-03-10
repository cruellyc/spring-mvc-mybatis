package com.doorlock.db;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.doorlock.entity.DemoDAO;
import com.doorlock.service.IDemoService;

/**
 *
 * @author liyc
 * @date 2017年3月10日 下午2:06:38
 */
public class TestMain {
	/**
	 * 测试代码
	 * 
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("conf/spring.xml");
		IDemoService service1 = (IDemoService) context.getBean("iDemoService");

		// 创建任务对象
		DBTask task1 = new DBTask("test1", service1, "selectGroup");
		DBTask task2 = new DBTask("test2", service1, "selectGroup");
		DBTask task3 = new DBTask("test3", service1, "selectGroup");

		DemoDAO demo = new DemoDAO();
		demo.setA(2);
		demo.setB("12121212");
		demo.setC(100);
		DBTask taskInsert1 = new DBTask("test1", service1, "insertDemo", demo);
		DBTask taskInsert2 = new DBTask("test2", service1, "insertDemo", demo);
		DBTask taskInsert3 = new DBTask("test3", service1, "insertDemo", demo);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		List<DBTask> taskInsertList = new ArrayList<DBTask>();
		taskInsertList.add(taskInsert1);
		taskInsertList.add(taskInsert2);
		taskInsertList.add(taskInsert3);
		System.out.println("开始插入数据:" + format.format(new Date()));
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

		System.out.println("插入数据结束:" + format.format(new Date()));


		List<DBTask> taskList = new ArrayList<DBTask>();
		taskList.add(task1);
		taskList.add(task2);
		taskList.add(task3);

		System.out.println("开始查询3个数据表：" + format.format(new Date()));
		for (DBTask task : taskList) {
			DBTaskMgr.instance().excute(task);
		}

		while (true) {
			int success = 0;
			for (DBTask task : taskList) {
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

			if (success == taskList.size()) {
				break;
			}
		}

		for (DBTask task : taskList) {
			System.out.println(task.getRetValue());
		}

		System.out.println("3个数据表查询结束：" + format.format(new Date()));
	}
}
