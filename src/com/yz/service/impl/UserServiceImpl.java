package com.yz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yz.mapper.UserMapper;
import com.yz.po.User;
import com.yz.po.UserCustom;
import com.yz.po.UserQueryVo;
import com.yz.service.UserService;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public void insert(User user) throws Exception {
		userMapper.insert(user);
		
	}

	@Override
	public void deleteUserById(Integer id) throws Exception {
		userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public User findUserById(Integer id) throws Exception {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateUser(Integer id, UserCustom userCustom) throws Exception {
		userCustom.setId(id);
		userMapper.updateByPrimaryKeySelective(userCustom);
		
	}

	@Override
	public List<User> findUserList(UserQueryVo userQueryVo) throws Exception {
		return null;
	}

}
