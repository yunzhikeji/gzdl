package com.yz.facecloud.model;

import java.util.List;

/*
 * 摄像机返回信息
 */
public class CameraResultMessage extends BasicResultMessage{

	private int total_count;//满足搜索条件的结果的数量
	private int list_size;//返回的结果列表大小
	private List<CameraMessage> camera_list;
	

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
