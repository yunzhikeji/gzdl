package com.yz.facecloud.model;

import java.util.List;

public class FaceResultMessage extends BasicResultMessage {

	private int total_count;// 满足搜索条件的结果的数量
	private int list_size;// 返回的结果列表大小
	private List<FaceMessage> face_list;

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

	public List<FaceMessage> getFace_list() {
		return face_list;
	}

	public void setFace_list(List<FaceMessage> face_list) {
		this.face_list = face_list;
	}

}
