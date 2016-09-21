package com.yz.utils;

import java.util.List;

import com.yz.facecloud.model.CameraMessage;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

public class TestMain {
	
	public static void main(String[] args) {
		
		int a = -1;
		System.out.println(a);
		
		System.out.println(-1!=0);
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
