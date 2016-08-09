package com.yz.facecloud.service.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

import com.yz.facecloud.model.LoginRequestMessage;
import com.yz.facecloud.model.LoginResultMessage;
import com.yz.facecloud.service.IHttpRequestService;
import com.yz.facecloud.util.MD5Util;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class HttpRequestServiceImpl implements IHttpRequestService {

	private static Logger log = Logger.getLogger(HttpRequestServiceImpl.class);
	public static final String LOGIN_REQUEST_URL = "base/auth/login";
	public static final String LOGOUT_REQUEST_URL = "base/auth/logout";
	public static final String GET_FACEDB_REQUEST_URL = "facedb";
	public static final String RECOGNIZE_REQUEST_URL = "faceops/image_recognition";
	public static final String ADD_PERSON_REQUEST_URL = "facedb/";

	private String serverAddress;

	/**
	 * 用户登陆
	 * 
	 * @param name,password
	 * 
	 * @return loginResultMessage 登陆返回信息
	 */
	public LoginResultMessage login(String name, String password) {

		LoginResultMessage resultMsg = new LoginResultMessage();
		String login_url = serverAddress + LOGIN_REQUEST_URL;

		LoginRequestMessage loginMes = new LoginRequestMessage();
		loginMes.setUser_name(name);
		loginMes.setUser_pwd(MD5Util.getMD5(password));
		loginMes.setModel("force_login");

		String requestMsg = JSONObject.fromObject(loginMes).toString();

		JSONObject jsonObject = httpRequest(login_url, "POST", requestMsg);
		// 如果请求成功
		if (null != jsonObject) {
			try {

				resultMsg.setRet(jsonObject.getInt("ret"));
				resultMsg.setRet_mes(jsonObject.getString("ret_mes"));
				resultMsg.setSessionid(jsonObject.getString("sessionid"));
				resultMsg.setVersion(jsonObject.getString("version"));
				resultMsg.setServer_time(jsonObject.getString("server_time"));

			} catch (JSONException e) {

			}
		}

		return resultMsg;
	}

	/**
	 * 用户注销
	 * 
	 * 
	 * @return loginResultMessage 登陆返回信息
	 */
	public LoginResultMessage logout() {

		LoginResultMessage resultMsg = new LoginResultMessage();
		String logout_url = serverAddress + LOGIN_REQUEST_URL;

		JSONObject jsonObject = httpRequest(logout_url, "POST", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {

				resultMsg.setRet(jsonObject.getInt("ret"));
				resultMsg.setRet_mes(jsonObject.getString("ret_mes"));

			} catch (JSONException e) {

			}
		}

		return resultMsg;
	}

	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {

			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			log.error(" server connection timed out.");
		} catch (Exception e) {
			log.error("http request error:{}", e);
		}
		return jsonObject;
	}

	public String getServerAddress() {
		return serverAddress;
	}

	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}
	
	

}
