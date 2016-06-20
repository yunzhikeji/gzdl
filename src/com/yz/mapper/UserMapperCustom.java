package com.yz.mapper;

import com.yz.po.User;

public interface UserMapperCustom {
	User findByUserNameAndPassword(String username,String password);

}
