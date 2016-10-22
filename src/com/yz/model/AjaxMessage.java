package com.yz.model;

import java.util.List;

import com.yz.facecloud.vo.AlarmMessageVO;

public class AjaxMessage {
	
	private Integer errorCode;//0:cameraid=0 则不需要再进行获取告警记录了 1:布控成功 2:未开启布控状态 3:人脸服务器连接超时

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

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	
	

}
