package com.yz.facecloud.model;

/*
 * 登陆返回信息
 */
public class LoginResultMessage  extends BasicResultMessage{

	private String sessionid;
	private String version;
	private String server_time;

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getServer_time() {
		return server_time;
	}

	public void setServer_time(String server_time) {
		this.server_time = server_time;
	}

}
