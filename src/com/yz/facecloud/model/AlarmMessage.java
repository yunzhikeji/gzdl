package com.yz.facecloud.model;

import java.util.List;

/*
 * 
 */
public class AlarmMessage {

	private String alarm_id;// 告警ID，不指定缺省 多个1,2,3
	private int camera_id; // 相机id
	private String camera_name;// 
	private String alarm_time;
	private int monitor_type;// 布控类型
	private int alarm_type;// 0,抓拍无告警1,告警 9所有//查询时不分黑白名单告警，所用1包含黑白名单告警
	/****
	 * 黑名单的理解：就是人脸库中存在的
	 * 白名单的理解:就是人脸库中搜不到的
	 * 抓拍的理解：就是不进行人脸库搜索
	 * 
	 */
	private int alarm_level;
	private String photo_name;
	private String face_name;
	private double face_blur;
	private int photo_host_id;
	private int state;// 处理状态（0：未处理，1：正常，2：误报，3：已处理（包括1和2），4：正常并入库，指定所有状态时不传state即可）
	private List<SearchMessage> searchMessages;

	public String getCamera_name() {
		return camera_name;
	}

	public void setCamera_name(String camera_name) {
		this.camera_name = camera_name;
	}

	public String getAlarm_id() {
		return alarm_id;
	}

	public void setAlarm_id(String alarm_id) {
		this.alarm_id = alarm_id;
	}

	public int getCamera_id() {
		return camera_id;
	}

	public void setCamera_id(int camera_id) {
		this.camera_id = camera_id;
	}

	public String getAlarm_time() {
		return alarm_time;
	}

	public void setAlarm_time(String alarm_time) {
		this.alarm_time = alarm_time;
	}

	public int getMonitor_type() {
		return monitor_type;
	}

	public void setMonitor_type(int monitor_type) {
		this.monitor_type = monitor_type;
	}

	public int getAlarm_type() {
		return alarm_type;
	}

	public void setAlarm_type(int alarm_type) {
		this.alarm_type = alarm_type;
	}

	public int getAlarm_level() {
		return alarm_level;
	}

	public void setAlarm_level(int alarm_level) {
		this.alarm_level = alarm_level;
	}

	public String getPhoto_name() {
		return photo_name;
	}

	public void setPhoto_name(String photo_name) {
		this.photo_name = photo_name;
	}

	public String getFace_name() {
		return face_name;
	}

	public void setFace_name(String face_name) {
		this.face_name = face_name;
	}

	public double getFace_blur() {
		return face_blur;
	}

	public void setFace_blur(double face_blur) {
		this.face_blur = face_blur;
	}

	public int getPhoto_host_id() {
		return photo_host_id;
	}

	public void setPhoto_host_id(int photo_host_id) {
		this.photo_host_id = photo_host_id;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public List<SearchMessage> getSearchMessages() {
		return searchMessages;
	}

	public void setSearchMessages(List<SearchMessage> searchMessages) {
		this.searchMessages = searchMessages;
	}

}
