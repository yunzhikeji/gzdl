package com.yz.facecloud.model;

/**
 * 告警请求参数封装
 * 
 * @author Administrator
 *
 */
public class AlarmRequestMessage extends BasicRequestMessage{

	private String alarm_id;// 告警ID，不指定缺省 多个1+2+3
	private int monitor_type;// 布控类型
	private int alarm_type;// 0,抓拍无告警1,告警 9所有//查询时不分黑白名单告警，所用1包含黑白名单告警
	private String time_start;// 开始时间 "2013/03/15+10:40:12"
	private String time_end;// 结束时间 "2013/03/16+10:40:12"
	private String db_id_list;// 告警人脸库
	private String camera_id_list;// 摄像机编号，多个23+34+56
	private int state;// 处理状态（0：未处理，1：正常，2：误报，3：已处理（包括1和2），4：正常并入库，指定所有状态时不传state即可）
	private String last_alarm_id;// 最新告警ID，有这个参数表示读取最新告警记录
	private String max_alarm_id;// 最大告警ID，有这个参数表示读取<= max_alarm_id

	public String getAlarm_id() {
		return alarm_id;
	}

	public void setAlarm_id(String alarm_id) {
		this.alarm_id = alarm_id;
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

	public String getTime_start() {
		return time_start;
	}

	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}

	public String getTime_end() {
		return time_end;
	}

	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}

	public String getDb_id_list() {
		return db_id_list;
	}

	public void setDb_id_list(String db_id_list) {
		this.db_id_list = db_id_list;
	}

	public String getCamera_id_list() {
		return camera_id_list;
	}

	public void setCamera_id_list(String camera_id_list) {
		this.camera_id_list = camera_id_list;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getLast_alarm_id() {
		return last_alarm_id;
	}

	public void setLast_alarm_id(String last_alarm_id) {
		this.last_alarm_id = last_alarm_id;
	}

	public String getMax_alarm_id() {
		return max_alarm_id;
	}

	public void setMax_alarm_id(String max_alarm_id) {
		this.max_alarm_id = max_alarm_id;
	}

}
