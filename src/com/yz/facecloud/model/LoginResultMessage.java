package com.yz.facecloud.model;

public class LoginResultMessage {

	private int ret;
	private String ret_mes;
	private String sessionid;
	private String version;
	private String server_time;

	public int getRet() {
		return ret;
	}

	public void setRet(int ret) {
		this.ret = ret;
	}

	public String getRet_mes() {
		return ret_mes;
	}

	public void setRet_mes(String ret_mes) {
		this.ret_mes = ret_mes;
	}

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
