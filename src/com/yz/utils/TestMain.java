package com.yz.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.yz.facecloud.model.CameraMessage;
import com.yz.facecloud.model.SearchMessage;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

public class TestMain {
	
	private static final Comparator<SearchMessage> COMPARATOR = new Comparator<SearchMessage>() {
		public int compare(SearchMessage o1, SearchMessage o2) {
			return o1.compareTo(o2);// 运用SearchMessage类的compareTo方法比较两个对象
		}
	};
	
	
	
	public static void main(String[] args) {
		
		List<SearchMessage> searchMessages = new ArrayList<SearchMessage>();
		
		
		
		SearchMessage message1 = new SearchMessage();
		message1.setSimilarity(170.5);
		searchMessages.add(message1);
		SearchMessage message2 = new SearchMessage();
		message2.setSimilarity(70.6);
		searchMessages.add(message2);
		SearchMessage message3 = new SearchMessage();
		message3.setSimilarity(720.7);
		searchMessages.add(message3);
		
		
		for (int i = 0; i < searchMessages.size(); i++) {
			System.out.println(searchMessages.get(i).getSimilarity());
		}
		
		
		Collections.sort(searchMessages, COMPARATOR);
		
		
		for (int i = 0; i < searchMessages.size(); i++) {
			System.out.println(searchMessages.get(i).getSimilarity());
		}
		
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
