package com.yz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yz.mapper.OrganizeMapperCustom;
import com.yz.mapper.UserMapper;
import com.yz.mapper.UserMapperCustom;
import com.yz.po.Organize;
import com.yz.po.User;
import com.yz.po.UserCustom;
import com.yz.po.UserQueryVo;
import com.yz.service.OrganizeService;
import com.yz.service.UserService;
import com.yz.vo.OrganizeQueryVO;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserMapperCustom userMapperCustom;

	@Autowired
	private OrganizeService organizeService;

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
	public List<User> findUserList() throws Exception {

		return userMapperCustom.findUserList();
	}

	@Override
	public User findByUserNameAndPassword(UserQueryVo userQueryVo) throws Exception {

		User user = userMapperCustom.findByUserNameAndPassword(userQueryVo);

		return user;
	}

	@Override
	public void deleteUserByOrganizeid(Integer id) {
		userMapperCustom.deleteUserByOrganizeid(id);

	}

	@Override
	public List<User> findUserByUsername(String username) throws Exception {
		return userMapperCustom.findUserByUsername(username);
	}

	@Override
	public List<User> getUsersByOrganizeQueryVO(OrganizeQueryVO organizeQueryVO) {
		// TODO Auto-generated method stub

		List<User> users = new ArrayList<User>();
		
		List<User> commonUserList = new ArrayList<User>();

		List<Organize> organizes = organizeService.getOrganizesByOrganizeQueryVO(organizeQueryVO);// 根据查询条件获得组织列表
		
		if(organizes!=null&&organizes.size()>0)
		{
			 commonUserList = userMapperCustom.findUserListByOrganizes(organizes);// 根据组织列表查询用户列表
		}

		users.addAll(commonUserList);
				
		return users;

	}

}
