package com.yz.facecloud.model;

import java.util.List;

/*
 * 告警返回信息
 */
public class AlarmResultMessage extends BasicResultMessage{

	private int alarm_size;
	private List<AlarmMessage> alarmMessages;
	private int delete_result;//删除时返回状态


	public int getAlarm_size() {
		return alarm_size;
	}

	public void setAlarm_size(int alarm_size) {
		this.alarm_size = alarm_size;
	}

	public List<AlarmMessage> getAlarmMessages() {
		return alarmMessages;
	}

	public void setAlarmMessages(List<AlarmMessage> alarmMessages) {
		this.alarmMessages = alarmMessages;
	}

	public int getDelete_result() {
		return delete_result;
	}

	public void setDelete_result(int delete_result) {
		this.delete_result = delete_result;
	}
	
	

}
