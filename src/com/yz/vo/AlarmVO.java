package com.yz.vo;

import com.yz.po.Alarm;

public class AlarmVO extends Alarm {

	private String camName;

	private String perTypename;

	public String getCamName() {
		return camName;
	}

	public void setCamName(String camName) {
		this.camName = camName;
	}

	public String getPerTypename() {
		return perTypename;
	}

	public void setPerTypename(String perTypename) {
		this.perTypename = perTypename;
	}

}
