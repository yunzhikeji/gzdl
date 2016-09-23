package com.yz.service;

import java.util.List;

import com.yz.po.Organize;
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
	public List<User> findUserList() throws Exception;
	
	// 修改用户 信息
	
	public void updateUser(Integer id,UserCustom userCustom)throws Exception;
	
	//验证用户登录
	public User findByUserNameAndPassword(UserQueryVo userQueryVo) throws Exception;

	// 根据用户的organizeId查询用户所属的组织信息
	public Organize findOrganizeByOrganizeId(Integer organizeid) throws Exception;

	public void deleteUserByOrganizeId(Integer id);


}
