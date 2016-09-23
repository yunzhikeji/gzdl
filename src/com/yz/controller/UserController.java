package com.yz.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Null;

import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jackson.map.util.BeanUtil;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yz.po.Organize;
import com.yz.po.User;
import com.yz.po.UserCustom;
import com.yz.service.OrganizeService;
import com.yz.service.UserService;
import com.yz.vo.UserVo;
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrganizeService organizeService;
	
	@RequestMapping("/queryUsers")
	public ModelAndView queryUsers(HttpServletRequest request) throws Exception{
		//调用service查询数据库，查询用户列表
		List<User> userList = userService.findUserList();
		
		
		//返回用户信息同时要返回用户所属机构的信息，创建一个UserVo对象,根据organizeId查询organize
		List<UserVo> userVoList = new ArrayList<UserVo>();
		for (int i=0;userList !=null && i < userList.size();i++ ){
			User user = userList.get(i);
			UserVo userVo = new UserVo();
			userVo.setId(user.getId());
			userVo.setOrganizeid(user.getOrganizeid());
			userVo.setOrganizeName(userService.findOrganizeByOrganizeId(user.getOrganizeid()).getName());
			userVo.setProvince(userService.findOrganizeByOrganizeId(user.getOrganizeid()).getProvince());
			userVo.setCity(userService.findOrganizeByOrganizeId(user.getOrganizeid()).getCity());
			userVo.setArea(userService.findOrganizeByOrganizeId(user.getOrganizeid()).getArea());
			userVo.setType(userService.findOrganizeByOrganizeId(user.getOrganizeid()).getType());
			userVo.setPassword(user.getPassword());
			userVo.setUsername(user.getUsername());
			userVo.setRealname(user.getRealname());
			userVoList.add(userVo);
		}
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("userVoList", userVoList);
		modelAndView.setViewName("user/user");
		return modelAndView;
		
	}
	//去添加用户页面，同时回显当前数据库中的组织列表
	@RequestMapping("/toAddUser")
	public String toAddUser(HttpServletRequest request,Model model) throws Exception{
		List<Organize> organizeList = organizeService.findOrganizeList();
		model.addAttribute("organizeList", organizeList);
		return "user/userAdd";
		
	}
	
	//去修改用户页面，并回显所修改的用户信息
	@RequestMapping("/toEditUser")
	public String toEditUser(HttpServletRequest request,Model model,Integer id) throws Exception{
		User user = userService.findUserById(id);
		model.addAttribute("user", user);
		return "user/userEdit";
		
	}
	
	@RequestMapping("/editUserSubimt")
	public String editUserSubimt(Model model,HttpServletRequest request,Integer id,UserCustom userCustom)throws Exception{

		userService.updateUser(id, userCustom);
		return "success";
	}
	
	// 请求添加一个用户
	@RequestMapping("/addUser")
	public String addUser(HttpServletRequest request,String username,String organizeName)throws Exception{
		Organize organize = organizeService.findOrganizeByName(organizeName);
		User user = new User();
		user.setUsername(username);
		user.setOrganizeid(organize.getId());
		user.setPassword("1234");
		userService.insert(user);
		return "redirect:/user/queryUsers";
	}
	
	// 请求删除一个用户
	@RequestMapping("/deleteUser")
	public String deleteUser(HttpServletRequest request,Integer id) throws Exception {
		userService.deleteUserById(id);
		return "redirect:/user/queryUsers";
	}
	
	

}
