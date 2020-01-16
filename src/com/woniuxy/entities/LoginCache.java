package com.woniuxy.entities;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class LoginCache {
	private static LoginCache instance = new LoginCache();
	private Map<String,HttpSession> map = new HashMap<String,HttpSession>();
	private LoginCache() {
		
	}
	public static LoginCache getInstance() {
		return instance;
	}
	public void setMap(String userName,HttpSession session) {
		map.put(userName, session);
	}
	public HttpSession getSession(String userName) {
		return map.get(userName);
	}
}
