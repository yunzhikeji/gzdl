package com.yz.mapper;

import java.util.List;

import com.yz.po.Alarm;
import com.yz.vo.AlarmQueryVO;

public interface AlarmMapperCustom {

	List<Alarm> findAlarmList();

	List<Alarm> getAlarmsByAlarmQueryVO(AlarmQueryVO alarmQueryVO);
}