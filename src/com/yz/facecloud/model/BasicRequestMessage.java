package com.yz.facecloud.model;

public class BasicRequestMessage {

	private int skip;// 跳过前面多少个查询结果，如果不指定该参数，默认skip=0
	private int top;//最多显示多少个查询结果，如果不指定该参数，默认top=300

	public int getSkip() {
		return skip;
	}

	public void setSkip(int skip) {
		this.skip = skip;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

}
