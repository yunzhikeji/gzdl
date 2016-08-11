package com.yz.facecloud.model;

public class PolicyMessage {

	private int mt_policy_id;
	private String mt_policy_name;
	private int monitor_type;
	private String alarm_type;
	private int time_schema_id;
	private Float similarity;// 80.0
	private Float similarity2;// 80.0
	private Float similarity3;// 80.0
	private int max_return;// 10 (<50),
	private int scene_saved;// 可选,默认都保存
	private int min_pixel;// 可选,单位为象素
	private int max_face_num;// 12,
	private Float zoom_times;
	private int face_storage_day;// 30
	private int best_face; // 0:普通抓拍；1：最佳人脸抓拍
	private int speed_mode;// 0:正常模式 1:分片模式 只对ftp有效，//可选，不填时服务端以0处理
	private int face_adjust;// 可选，不填时服务端以0处理，
	private int min_point;// 10，
	private int skip_dup;// 0:不进行人脸去重分析，1:进行人脸去重，//可选，不填时服务端以0处理，
	private int skip_time; // 去重时间周期，单位秒，表示这个时间周期内有重复的人脸则不再抓拍

	public int getMt_policy_id() {
		return mt_policy_id;
	}

	public void setMt_policy_id(int mt_policy_id) {
		this.mt_policy_id = mt_policy_id;
	}

	public String getMt_policy_name() {
		return mt_policy_name;
	}

	public void setMt_policy_name(String mt_policy_name) {
		this.mt_policy_name = mt_policy_name;
	}

	public int getMonitor_type() {
		return monitor_type;
	}

	public void setMonitor_type(int monitor_type) {
		this.monitor_type = monitor_type;
	}

	public String getAlarm_type() {
		return alarm_type;
	}

	public void setAlarm_type(String alarm_type) {
		this.alarm_type = alarm_type;
	}

	public int getTime_schema_id() {
		return time_schema_id;
	}

	public void setTime_schema_id(int time_schema_id) {
		this.time_schema_id = time_schema_id;
	}

	public Float getSimilarity() {
		return similarity;
	}

	public void setSimilarity(Float similarity) {
		this.similarity = similarity;
	}

	public Float getSimilarity2() {
		return similarity2;
	}

	public void setSimilarity2(Float similarity2) {
		this.similarity2 = similarity2;
	}

	public Float getSimilarity3() {
		return similarity3;
	}

	public void setSimilarity3(Float similarity3) {
		this.similarity3 = similarity3;
	}

	public int getMax_return() {
		return max_return;
	}

	public void setMax_return(int max_return) {
		this.max_return = max_return;
	}

	public int getScene_saved() {
		return scene_saved;
	}

	public void setScene_saved(int scene_saved) {
		this.scene_saved = scene_saved;
	}

	public int getMin_pixel() {
		return min_pixel;
	}

	public void setMin_pixel(int min_pixel) {
		this.min_pixel = min_pixel;
	}

	public int getMax_face_num() {
		return max_face_num;
	}

	public void setMax_face_num(int max_face_num) {
		this.max_face_num = max_face_num;
	}

	public Float getZoom_times() {
		return zoom_times;
	}

	public void setZoom_times(Float zoom_times) {
		this.zoom_times = zoom_times;
	}

	public int getFace_storage_day() {
		return face_storage_day;
	}

	public void setFace_storage_day(int face_storage_day) {
		this.face_storage_day = face_storage_day;
	}

	public int getBest_face() {
		return best_face;
	}

	public void setBest_face(int best_face) {
		this.best_face = best_face;
	}

	public int getSpeed_mode() {
		return speed_mode;
	}

	public void setSpeed_mode(int speed_mode) {
		this.speed_mode = speed_mode;
	}

	public int getFace_adjust() {
		return face_adjust;
	}

	public void setFace_adjust(int face_adjust) {
		this.face_adjust = face_adjust;
	}

	public int getMin_point() {
		return min_point;
	}

	public void setMin_point(int min_point) {
		this.min_point = min_point;
	}

	public int getSkip_dup() {
		return skip_dup;
	}

	public void setSkip_dup(int skip_dup) {
		this.skip_dup = skip_dup;
	}

	public int getSkip_time() {
		return skip_time;
	}

	public void setSkip_time(int skip_time) {
		this.skip_time = skip_time;
	}

}
