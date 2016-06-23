package com.yz.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yz.po.User;
import com.yz.service.UserService;
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/queryUsers")
	public ModelAndView queryUsers(HttpServletRequest request) throws Exception{
		
		//调用service查询数据库，查询用户列表
		List<User> userList = userService.findUserList();
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("userList", userList);
		modelAndView.setViewName("user");
		return modelAndView;
		
	}

}
