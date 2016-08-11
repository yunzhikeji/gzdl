package com.yz.facecloud.model;

public class CameraRequestMessage extends BasicRequestMessage {

	private int host_id;// 服务器编号，带该参数时表示这台服务器连接的摄像机
	private int camera_id;// 摄像机ID

	public int getHost_id() {
		return host_id;
	}

	public void setHost_id(int host_id) {
		this.host_id = host_id;
	}

	public int getCamera_id() {
		return camera_id;
	}

	public void setCamera_id(int camera_id) {
		this.camera_id = camera_id;
	}

}
