package com.yz.facecloud.model;

import java.util.List;

/**
 * 人脸识别返回信息-人脸识别返回列表
 * 
 * @author Administrator
 *
 */
public class RecognitionMessage {

	private PersonRectMessage personRectMessage;

	private List<MatchingMessage> matchingMessages;

	public PersonRectMessage getPersonRectMessage() {
		return personRectMessage;
	}

	public void setPersonRectMessage(PersonRectMessage personRectMessage) {
		this.personRectMessage = personRectMessage;
	}

	public List<MatchingMessage> getMatchingMessages() {
		return matchingMessages;
	}

	public void setMatchingMessages(List<MatchingMessage> matchingMessages) {
		this.matchingMessages = matchingMessages;
	}

}
