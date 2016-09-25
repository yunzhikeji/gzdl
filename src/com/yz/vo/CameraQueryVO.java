package com.yz.vo;

/**
 * 查询条件包装类  设备
 * @author Administrator
 *
 */
public class CameraQueryVO {

	private String number;//前端所上传设备编号

	private Integer userOrganizeid;//一般指当前用户所在组织id

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getUserOrganizeid() {
		return userOrganizeid;
	}

	public void setUserOrganizeid(Integer userOrganizeid) {
		this.userOrganizeid = userOrganizeid;
	}

	

}
