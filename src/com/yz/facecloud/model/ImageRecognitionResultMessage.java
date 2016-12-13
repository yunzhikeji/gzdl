package com.yz.facecloud.model;

import java.util.List;

/*
 * 人脸识别返回信息
 */
public class ImageRecognitionResultMessage extends BasicResultMessage {

	private Integer list_size;
	private List<RecognitionMessage> recognitionMessages;
	private Integer blur;

	public Integer getList_size() {
		return list_size;
	}

	public void setList_size(Integer list_size) {
		this.list_size = list_size;
	}

	public List<RecognitionMessage> getRecognitionMessages() {
		return recognitionMessages;
	}

	public void setRecognitionMessages(List<RecognitionMessage> recognitionMessages) {
		this.recognitionMessages = recognitionMessages;
	}

	public Integer getBlur() {
		return blur;
	}

	public void setBlur(Integer blur) {
		this.blur = blur;
	}

}
