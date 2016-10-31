package com.yz.facecloud.vo;

public class AlarmMessageVO {

	private String alarm_id;
	private String camera_name;
	private String alarm_time;
	private String alarm_typename;
	private String photo_name;
	private String person_name;
	private String photo_url;
	private String person_id;
	private int photo_host_id;

	public String getAlarm_id() {
		return alarm_id;
	}

	public void setAlarm_id(String alarm_id) {
		this.alarm_id = alarm_id;
	}

	public String getCamera_name() {
		return camera_name;
	}

	public void setCamera_name(String camera_name) {
		this.camera_name = camera_name;
	}

	public String getAlarm_time() {
		return alarm_time;
	}

	public void setAlarm_time(String alarm_time) {
		this.alarm_time = alarm_time;
	}

	public String getAlarm_typename() {
		return alarm_typename;
	}

	public void setAlarm_typename(String alarm_typename) {
		this.alarm_typename = alarm_typename;
	}

	public String getPhoto_name() {
		return photo_name;
	}

	public void setPhoto_name(String photo_name) {
		this.photo_name = photo_name;
	}

	public String getPerson_name() {
		return person_name;
	}

	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}

	public String getPhoto_url() {
		return photo_url;
	}

	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}

	public String getPerson_id() {
		return person_id;
	}

	public void setPerson_id(String person_id) {
		this.person_id = person_id;
	}

	public int getPhoto_host_id() {
		return photo_host_id;
	}

	public void setPhoto_host_id(int photo_host_id) {
		this.photo_host_id = photo_host_id;
	}

}
