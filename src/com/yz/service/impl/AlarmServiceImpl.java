package com.yz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yz.mapper.AlarmMapper;
import com.yz.mapper.AlarmMapperCustom;
import com.yz.po.Alarm;
import com.yz.service.AlarmService;
import com.yz.vo.AlarmQueryVO;

public class AlarmServiceImpl implements AlarmService {

	@Autowired
	private AlarmMapper alarmMapper;

	@Autowired
	private AlarmMapperCustom alarmMapperCustom;

	@Override
	public Alarm findAlarmById(Integer id) throws Exception {

		return alarmMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateAlarm(Integer id, Alarm alarm) throws Exception {
		alarm.setId(id);
		alarmMapper.updateByPrimaryKey(alarm);

	}

	@Override
	public void saveAlarm(Alarm alarm) throws Exception {
		alarmMapper.insert(alarm);
	}

	@Override
	public void deleteAlarmById(Integer id) throws Exception {
		alarmMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Alarm> findAlarmList() throws Exception {

		return alarmMapperCustom.findAlarmList();
	}

	@Override
	public List<Alarm> getAlarmsByAlarmQueryVO(AlarmQueryVO alarmQueryVO) {
		// TODO Auto-generated method stub
		return alarmMapperCustom.getAlarmsByAlarmQueryVO(alarmQueryVO);
	}

}
