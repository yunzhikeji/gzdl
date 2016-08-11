package com.yz.facecloud.model;

public class PolicyRequestMessage extends BasicRequestMessage{
	
	private int mt_policy_id;//指定读取具体的布控策略

	public int getMt_policy_id() {
		return mt_policy_id;
	}
	public void setMt_policy_id(int mt_policy_id) {
		this.mt_policy_id = mt_policy_id;
	}
	

}
