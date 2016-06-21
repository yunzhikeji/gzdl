package com.yz.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yz.po.User;
import com.yz.po.UserCustom;
import com.yz.po.UserQueryVo;
import com.yz.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	// 登陆
	@RequestMapping("/login")
	public String login(HttpSession session,UserQueryVo userQueryVo,Model model)
			throws Exception {

		// 调用service进行用户身份验证
		User userQuery = userService.findByUserNameAndPassword(userQueryVo);
		if (userQuery == null){
			model.addAttribute("loginError", "用户名或密码不正确！");
		return "login";	
		}else{
		// 在session中保存用户身份信息
		session.setAttribute("username", userQueryVo.getUser().getUsername());
		// 重定向到商品列表页面
		return "redirect:/index.action";
		}
	}

	// 退出
	@RequestMapping("/logout")
	public String logout(HttpSession session) throws Exception {

		// 清除session
		session.invalidate();

		// 重定向到商品列表页面
		return "redirect:/login";
	}

}
