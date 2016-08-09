package com.yz.facecloud.model;

import java.util.List;

public class CameraResultMessage {

	private int ret;
	private String ret_mes;
	private int total_count;
	private int list_size;
	private List<CameraMessage> camera_list;

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

	public int getTotal_count() {
		return total_count;
	}

	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}

	public int getList_size() {
		return list_size;
	}

	public void setList_size(int list_size) {
		this.list_size = list_size;
	}

	public List<CameraMessage> getCamera_list() {
		return camera_list;
	}

	public void setCamera_list(List<CameraMessage> camera_list) {
		this.camera_list = camera_list;
	}

}
