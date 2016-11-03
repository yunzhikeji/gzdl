package com.yz.service;

import java.util.List;

import com.yz.po.Alarm;
import com.yz.vo.AlarmQueryVO;

public interface AlarmService {
	
	public List<Alarm> findAlarmList() throws Exception;

	public Alarm findAlarmById(Integer id) throws Exception;

	public void updateAlarm(Integer id,Alarm alarm) throws Exception;
	
	public void saveAlarm(Alarm alarm) throws Exception;
	
	public void deleteAlarmById(Integer id) throws Exception;

	public List<Alarm> getAlarmsByAlarmQueryVO(AlarmQueryVO alarmQueryVO);
}
