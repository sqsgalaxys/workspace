package com.sldll.cms.vo;

public class Waiter {
	public String id;
	public String username;
	public String password;
	
	public Waiter(){
		super();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Waiter [username=" + username + ", password=" + password + "]";
	}
	
}
