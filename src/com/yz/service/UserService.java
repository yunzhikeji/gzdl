package com.yz.service;

import java.util.List;

import com.yz.po.User;
import com.yz.po.UserCustom;
import com.yz.po.UserQueryVo;

public interface UserService {

	// 添加一个用户
	public void insert(User user) throws Exception;

	// 删除一个用户
	public void deleteUserById(Integer id) throws Exception;

	// 根据id查询用户
	public User findUserById(Integer id) throws Exception;
	
	// 用户查询列表
	public List<User> findUserList(UserQueryVo userQueryVo) throws Exception;
	
	// 修改用户 信息
	
	public void updateUser(Integer id,UserCustom userCustom)throws Exception;

}