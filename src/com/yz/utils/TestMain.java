package com.yz.utils;

import java.util.ArrayList;
import java.util.List;

import com.yz.facecloud.model.CameraMessage;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

public class TestMain {
	
	public static void main(String[] args) {
		
			String url = "http://api.facecloud.reconova.com/facedb/1/persons/2/faces/100000000001/image";
			
			String serverAddress = "http://183.61.108.29:8000/"; 
			
			
			System.out.println(setUrl(url,serverAddress));
					
	}
	
	
	public static String setUrl(String url,String serverAddress) {
		// TODO Auto-generated method stub
		if(url.contains("http://"))
		{
			url = url.substring(7);
			int index = url.indexOf("/");
			if(index>0)
			{
				url = url.substring(index+1);
				return serverAddress+url;
			}
		}
		return null;
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
