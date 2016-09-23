package com.yz.utils;

import java.util.ArrayList;
import java.util.List;

import com.yz.facecloud.model.CameraMessage;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

public class TestMain {
	
	public static void main(String[] args) {
		

		List<Integer> number1 = new ArrayList<Integer>();
		
		number1.add(1);
		number1.add(2);
		number1.add(3);
		
		List<Integer> number2 = new ArrayList<Integer>();
		number2.add(1);
		number2.add(2);
		List<Integer> number3 = new ArrayList<Integer>();
		number3.addAll(number1);
		number3.addAll(number2);
		
		for(int i=0;i<number3.size();i++)
		{
			System.out.println(number3.get(i));
		}
		
			
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
