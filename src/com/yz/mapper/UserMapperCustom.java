package com.yz.mapper;

import com.yz.po.Organize;
import com.yz.po.User;
import com.yz.po.UserCustom;
import com.yz.po.UserQueryVo;
import java.util.List;

public interface UserMapperCustom {
	public User findByUserNameAndPassword(UserQueryVo userQueryVo) throws Exception;

	// 用户查询列表
	public List<User> findUserList() throws Exception;

	// 根据用户的organizeId查询用户所属的组织信息
	public Organize findOrganizeByOrganizeId(Integer organizeid) throws Exception;

	public void deleteUserByOrganizeId(Integer id);

	// 根据用户名查询用户信息，校验名称重复用,用list接收，防止数据库脏读数据
	public List<User> findUserByUsername(String username) throws Exception;

	// 根据组织列表查询用户
	public List<User> findUserListByOrganizes(List<Organize> organizes);

	// 根据organizeId列表查询用户
	public List<User> findUserListByOrganizeid(Integer organizeid);

}
