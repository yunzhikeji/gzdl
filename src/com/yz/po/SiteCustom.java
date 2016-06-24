package com.yz.po;

import java.util.ArrayList;
import java.util.List;

public class SiteCustom extends Site {

	private String sname;// 用于检查是否存在这个数据库

	private List<Camera> cameras;//用于存放当前工地适配的摄像头

	public SiteCustom() {
		cameras = new ArrayList<Camera>();
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public List<Camera> getCameras() {
		return cameras;
	}

	public void setCameras(List<Camera> cameras) {
		this.cameras = cameras;
	}

}
