package com.yz.mapper;

import com.yz.po.User;
import com.yz.po.UserCustom;

public interface UserMapperCustom {
	User findByUserNameAndPassword(UserCustom userCustom);

}
