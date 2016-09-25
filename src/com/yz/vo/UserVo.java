package com.yz.vo;

/**
 * userList列表显示所用包装类
 * 
 * @author Administrator
 *
 */
public class UserVO {

	private Integer id;

	private String organizeName;

	private String areaName;

	private String username;

	private String realname;

	private String password;

	private Integer organizeid;

	public String getOrganizeName() {
		return organizeName;
	}

	public void setOrganizeName(String organizeName) {
		this.organizeName = organizeName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getOrganizeid() {
		return organizeid;
	}

	public void setOrganizeid(Integer organizeid) {
		this.organizeid = organizeid;
	}

}
