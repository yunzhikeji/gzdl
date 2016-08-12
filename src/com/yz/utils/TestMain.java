package com.yz.utils;

import java.util.List;

import com.yz.facecloud.model.CameraMessage;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

public class TestMain {
	
	public static void main(String[] args) {
		CameraMessage cameraMessage = new CameraMessage();
		
		cameraMessage.setCamera_name("bbbb");
		cameraMessage.setCamera_mode(0);
		cameraMessage.setUrl("rtsp://admin:12345@192.168.0.64/h264/ch1/main/av_stream");
		cameraMessage.setDb_id_list("7");
		cameraMessage.setNode_id(0);
		cameraMessage.setFixed_host(0);
		cameraMessage.setMt_policy_id(1);
		
		System.out.println(jsonMessage(cameraMessage));
	}
	
	
	public static String jsonMessage(Object object) {
		JsonConfig jsonConfig = new JsonConfig();
		PropertyFilter filter = new PropertyFilter() {
			public boolean apply(Object object, String fieldName, Object fieldValue) {
				if (fieldValue instanceof List) {
					List<Object> list = (List<Object>) fieldValue;
					if (list.size() == 0) {
						return true;
					}
				}
				if(fieldName.equals("camera_id"))
				{
					return null == fieldValue || "".equals(fieldValue)||fieldValue.equals(0);
				}else{
					return null == fieldValue || "".equals(fieldValue);
				}
				
			}
		};
		jsonConfig.setJsonPropertyFilter(filter);

		JSONObject jsonObject = JSONObject.fromObject(object, jsonConfig);
		return jsonObject.toString();
	}

}
