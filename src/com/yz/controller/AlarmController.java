package com.yz.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yz.po.Alarm;
import com.yz.service.AlarmService;
import com.yz.vo.AlarmQueryVO;

@Controller
@RequestMapping("/alarm")
public class AlarmController {
	@Autowired
	private AlarmService alarmService;

	@RequestMapping("/alarmList")
	public ModelAndView alarmList(AlarmQueryVO alarmQueryVO, HttpServletRequest request) throws Exception {

		List<Alarm> alarmList = alarmService.getAlarmsByAlarmQueryVO(alarmQueryVO);// 根据查询条件获得组织列表

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("alarmList", alarmList);
		modelAndView.setViewName("alarm/alarmList");

		return modelAndView;
	}

	// 请求添加一个组织
	@RequestMapping("/addAlarm")
	public String addAlarm(HttpServletRequest request, Alarm alarm) throws Exception {
		Alarm or = new Alarm();

		alarmService.saveAlarm(or);
		return "success";

	}

	// 请求删除一个组织,删除组织的同时，删除该组织下的所有用户
	@RequestMapping("/deleteAlarm")
	public String deleteAlarm(HttpServletRequest request, Integer id) throws Exception {

		alarmService.deleteAlarmById(id);
		return "redirect:/alarm/alarmList";

	}

	// 组织信息修改提交
	@RequestMapping("/updateAlarmSubmit")
	public String updateAlarmSubmit(Model model, HttpServletRequest request, Integer id, Alarm alarm) throws Exception {
		alarmService.updateAlarm(id, alarm);
		return "success";
	}

}
