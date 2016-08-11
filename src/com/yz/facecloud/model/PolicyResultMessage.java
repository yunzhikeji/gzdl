package com.yz.facecloud.model;

import java.util.List;

public class PolicyResultMessage extends BasicResultMessage {

	private int mt_policy_num;
	private List<PolicyMessage> policyMessages;

	public int getMt_policy_num() {
		return mt_policy_num;
	}

	public void setMt_policy_num(int mt_policy_num) {
		this.mt_policy_num = mt_policy_num;
	}

	public List<PolicyMessage> getPolicyMessages() {
		return policyMessages;
	}

	public void setPolicyMessages(List<PolicyMessage> policyMessages) {
		this.policyMessages = policyMessages;
	}

}
