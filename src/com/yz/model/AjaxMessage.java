package com.yz.model;

import java.util.List;

import com.yz.facecloud.vo.AlarmMessageVO;

public class AjaxMessage {

	private String message;

	private List<AlarmMessageVO> alarmMessages;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<AlarmMessageVO> getAlarmMessages() {
		return alarmMessages;
	}

	public void setAlarmMessages(List<AlarmMessageVO> alarmMessages) {
		this.alarmMessages = alarmMessages;
	}

}
