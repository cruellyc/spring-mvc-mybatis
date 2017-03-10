package com.doorlock.common;

import java.util.HashMap;
import java.util.Map;


/**
 * 用户profile，用于session保存用户信息
 * @author arlen.yeh
 */
public class Profile {
	private int id;
	private String mobile;
	private String name;
	private int backId;
	private Map<String, String> map;
	
	public int getId() {
		return id;
	}
	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void logout(){
		this.setId(0);
		this.setMobile(null);
		this.setName(null);
	}
	public void backlogout(){
		this.setMobile(null);
		this.setName(null);
		this.setBackId(0);
	}
	public Map<String, String> getMap() {
		if (map == null) {
			map = new HashMap<String, String>();
		}
		return map;
	}
	public String getRegCode() {
		return getMap().get("regCode");
	}
	public void setRegCode(String regCode) {
		getMap().put("regCode", regCode);
	}
	public void setAttribute(String key, String value) {
		getMap().put(key, value);
	}
	public String getAttribute(String key) {
		return getMap().get(key);
	}
	public int getBackId() {
		return backId;
	}
	public void setBackId(int backId) {
		this.backId = backId;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
