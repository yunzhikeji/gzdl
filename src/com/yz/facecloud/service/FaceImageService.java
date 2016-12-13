package com.yz.facecloud.service;

import com.yz.facecloud.model.ImageMessage;

public interface FaceImageService {
	
	//返回识别人员列表
	public String getPersonMatchingMessage(ImageMessage imageMessage);

}
