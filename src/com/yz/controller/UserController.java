package com.yz.controller;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.yz.vo.OrganizeQueryVO;
import com.yz.vo.UserVO;
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrganizeService organizeService;
	
	@RequestMapping("/queryUsers")
	public ModelAndView queryUsers(OrganizeQueryVO organizeQueryVO,HttpServletRequest request) throws Exception{
		
		List<Organize> organizes = new ArrayList<Organize>();
		
		organizes =  organizeService.getOrganizesByOrganizeQueryVO(organizeQueryVO);
		
		System.out.println(organizes.size());
		
		
		//调用service查询数据库，查询用户列表
		List<User> userList = userService.findUserList();
		
		//返回用户信息同时要返回用户所属机构的信息，创建一个UserVo对象,根据organizeId查询organize
		List<UserVO> userVOList = new ArrayList<UserVO>();
		for (int i=0;userList !=null && i < userList.size();i++ ){
			User user = userList.get(i);
			UserVO userVO = new UserVO();
			
			userVO.setId(user.getId());
			userVO.setOrganizeid(user.getOrganizeid());
			userVO.setRole(user.getRole());
			if(user.getOrganizeid()==0)
			{
				userVO.setOrganizeName("亿弘淼能源科技有限公司");
				userVO.setAreaName("全国");
			}else
			{
				Organize organize = userService.findOrganizeByOrganizeId(user.getOrganizeid());
				userVO.setOrganizeName(organize.getName());
				String areaName = "";
				if(organize.getProvince()!=null&&!organize.getProvince().trim().equals(""))
				{
					areaName = areaName + organize.getProvince();
				}
				if(organize.getCity()!=null&&!organize.getCity().trim().equals(""))
				{
					areaName = areaName + organize.getCity();
				}
				if(organize.getArea()!=null&&!organize.getArea().trim().equals(""))
				{
					areaName = areaName + organize.getArea();
				}
				userVO.setAreaName(areaName);
			}
			userVO.setPassword(user.getPassword());
			userVO.setUsername(user.getUsername());
			userVO.setRealname(user.getRealname());
			userVOList.add(userVO);
		}
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("userVOList", userVOList);
		modelAndView.setViewName("user/userList");
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
	public String addUser(HttpServletRequest request,String username,String organizeName,Integer role)throws Exception{
		
		
		User user = new User();
		user.setUsername(username);
		user.setPassword("1234");
		user.setRole(role);
		if(organizeName.equals("请选择所属机构"))
		{
			user.setOrganizeid(0);
		}else{
			Organize organize = organizeService.findOrganizeByName(organizeName);
			user.setOrganizeid(organize.getId());
		}
		userService.insert(user);
		return "redirect:/user/queryUsers";
	}
	
	// 请求删除一个用户
	@RequestMapping("/deleteUser")
	public String deleteUser(HttpServletRequest request,Integer id) throws Exception {
		userService.deleteUserById(id);
		return "redirect:/user/queryUsers";
	}
	
	// 校验用户名重复
	@RequestMapping("/validUsername")
	public void validUsername(String username,Writer out) throws Exception{
		
		//如果list长度为0，就说明名称不重复
		List<User> userList = userService.findUserByUsername(username);
		String flag = "no";
		if (userList.size() > 0){
			flag = "yes";
		}
		out.write(flag);
	}

}
