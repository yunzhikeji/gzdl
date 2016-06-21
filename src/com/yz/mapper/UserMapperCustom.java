package com.yz.mapper;

import com.yz.po.User;
import com.yz.po.UserCustom;
import com.yz.po.UserQueryVo;

public interface UserMapperCustom {
	User findByUserNameAndPassword(UserQueryVo userQueryVo);

}
