package com.yz.facecloud.model;

import java.util.List;

public class FaceDBResultMessage {

	private int ret;
	private String ret_mes;
	private int total_count;//满足搜索条件的结果的数量
	private int list_size;//返回的结果列表大小
	private List<FaceDBMessage> facedb_list;

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

	public List<FaceDBMessage> getFacedb_list() {
		return facedb_list;
	}

	public void setFacedb_list(List<FaceDBMessage> facedb_list) {
		this.facedb_list = facedb_list;
	}

}
