package com.yz.facecloud.model;

public class CameraMessage {

	private int camera_id;
	private String camera_name;// port 1
	private int camera_mode;// 0
	private String url;// admin:12345@192.168.0.64/h264/ch1/main/av_stream”
	private int camera_state;
	private int mt_policy_id;
	private String db_id_list;// "1+2+3"
	private int node_id;
	private int fixed_host;
	private int belong_host;// 当前摄像机挂载服务器,运行时信息,不可修改
	private int man_ctrl;// 0:自动模式1:手动模式,运行时信息,不可修改
	private int man_init_state;// 手动初始状态，含义同camera_state,运行时信息,不可修改
	private int alarm_count;// 相机自上次清零后的告警统计数
	private int snap_count;// 相机自上次清零后的抓拍数量
	private String last_clear_time;// ”2012/07/12 09:02:00” 上次清零时间

	public int getCamera_id() {
		return camera_id;
	}

	public void setCamera_id(int camera_id) {
		this.camera_id = camera_id;
	}

	public String getCamera_name() {
		return camera_name;
	}

	public void setCamera_name(String camera_name) {
		this.camera_name = camera_name;
	}

	public int getCamera_mode() {
		return camera_mode;
	}

	public void setCamera_mode(int camera_mode) {
		this.camera_mode = camera_mode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getCamera_state() {
		return camera_state;
	}

	public void setCamera_state(int camera_state) {
		this.camera_state = camera_state;
	}

	public int getMt_policy_id() {
		return mt_policy_id;
	}

	public void setMt_policy_id(int mt_policy_id) {
		this.mt_policy_id = mt_policy_id;
	}

	public String getDb_id_list() {
		return db_id_list;
	}

	public void setDb_id_list(String db_id_list) {
		this.db_id_list = db_id_list;
	}

	public int getNode_id() {
		return node_id;
	}

	public void setNode_id(int node_id) {
		this.node_id = node_id;
	}

	public int getFixed_host() {
		return fixed_host;
	}

	public void setFixed_host(int fixed_host) {
		this.fixed_host = fixed_host;
	}

	public int getBelong_host() {
		return belong_host;
	}

	public void setBelong_host(int belong_host) {
		this.belong_host = belong_host;
	}

	public int getMan_ctrl() {
		return man_ctrl;
	}

	public void setMan_ctrl(int man_ctrl) {
		this.man_ctrl = man_ctrl;
	}

	public int getMan_init_state() {
		return man_init_state;
	}

	public void setMan_init_state(int man_init_state) {
		this.man_init_state = man_init_state;
	}

	public int getAlarm_count() {
		return alarm_count;
	}

	public void setAlarm_count(int alarm_count) {
		this.alarm_count = alarm_count;
	}

	public int getSnap_count() {
		return snap_count;
	}

	public void setSnap_count(int snap_count) {
		this.snap_count = snap_count;
	}

	public String getLast_clear_time() {
		return last_clear_time;
	}

	public void setLast_clear_time(String last_clear_time) {
		this.last_clear_time = last_clear_time;
	}

}
