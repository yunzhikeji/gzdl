package com.yz.vo;

/*
 * 查询条件包装类  组织
 * @author Administrator
 */
public class OrganizeQueryVO {

	private String province;

	private String city;

	private String area;

	private Integer type;// 组织类型 1：供电局 2：施工单位

	private String name;

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

}
