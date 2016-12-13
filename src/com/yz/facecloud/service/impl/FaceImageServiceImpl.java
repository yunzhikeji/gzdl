package com.yz.facecloud.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yz.facecloud.model.ImageMessage;
import com.yz.facecloud.model.ImageRecognitionResultMessage;
import com.yz.facecloud.model.RecognitionMessage;
import com.yz.facecloud.service.FaceImageService;
import com.yz.facecloud.service.HttpRequestService;

public class FaceImageServiceImpl implements FaceImageService {

	@Autowired
	private HttpRequestService requestService;

	@Override
	public String getPersonMatchingMessage(ImageMessage imageMessage) {
		// TODO Auto-generated method stub

		ImageRecognitionResultMessage imageRecognitionResultMessage = requestService.getRecognition(imageMessage);

		List<RecognitionMessage> recognitionMessages = new ArrayList<RecognitionMessage>();

		if (imageRecognitionResultMessage.getList_size() > 0) {
			recognitionMessages = imageRecognitionResultMessage.getRecognitionMessages();
			return "匹配人脸数：" + recognitionMessages.size();
		}
		return "暂无人员";
	}

}
