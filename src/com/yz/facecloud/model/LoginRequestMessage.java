package com.yz.facecloud.model;

/*
 * 登陆请求信息封装
 */
public class LoginRequestMessage extends BasicRequestMessage{

	private String user_name;
	private String user_pwd;
	private String mode;

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_pwd() {
		return user_pwd;
	}

	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	

}
