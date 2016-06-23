package com.yz.mapper;

import com.yz.po.User;
import com.yz.po.UserCustom;
import com.yz.po.UserQueryVo;
import java.util.List;

public interface UserMapperCustom {
	public User findByUserNameAndPassword(UserQueryVo userQueryVo) throws Exception;
	
	//用户查询列表
	public List<User> findUserList() throws Exception;

}
