package com.yz.facecloud.service.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.yz.exception.CustomException;
import com.yz.facecloud.model.AlarmMessage;
import com.yz.facecloud.model.AlarmRequestMessage;
import com.yz.facecloud.model.AlarmResultMessage;
import com.yz.facecloud.model.BasicResultMessage;
import com.yz.facecloud.model.CameraMessage;
import com.yz.facecloud.model.CameraRequestMessage;
import com.yz.facecloud.model.CameraResultMessage;
import com.yz.facecloud.model.FaceDBMessage;
import com.yz.facecloud.model.FaceDBRequestMessage;
import com.yz.facecloud.model.FaceDBResultMessage;
import com.yz.facecloud.model.ImageMessage;
import com.yz.facecloud.model.ImageRequestMessage;
import com.yz.facecloud.model.ImageResultMessage;
import com.yz.facecloud.model.LoginRequestMessage;
import com.yz.facecloud.model.LoginResultMessage;
import com.yz.facecloud.model.PolicyMessage;
import com.yz.facecloud.model.PolicyRequestMessage;
import com.yz.facecloud.model.PolicyResultMessage;
import com.yz.facecloud.model.SearchMessage;
import com.yz.facecloud.service.HttpRequestService;
import com.yz.po.Face;
import com.yz.service.FaceService;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

public class HttpRequestServiceImpl implements HttpRequestService {

	private static Logger log = Logger.getLogger(HttpRequestServiceImpl.class);

	public static final String SYSTEM_REQUEST_URL = "sysmgr/reboot";
	public static final String LOGIN_REQUEST_URL = "base/auth/login";
	public static final String LOGOUT_REQUEST_URL = "base/auth/logout";
	public static final String POLICY_REQUEST_URL = "rtmonitor/mt_policy";
	public static final String CAMERA_REQUEST_URL = "rtmonitor/manage";
	public static final String ALARM_REQUEST_URL = "rtmonitor/alarm";
	public static final String IMAGE_REQUEST_URL = "rtmonitor/alarm/image/";

	public static final String FACEDB_REQUEST_URL = "facedb";

	public static final String RECOGNIZE_REQUEST_URL = "faceops/image_recognition";
	public static final String ADD_PERSON_REQUEST_URL = "facedb/";

	@Autowired
	private FaceService faceService;

	private String serverAddress;

	/*****************************************
	 * 系统操作 重启服务 只有超级管理员root可以执行
	 *
	 * 
	 * @return loginResultMessage 返回基本信息
	 */
	public BasicResultMessage reboot() {

		BasicResultMessage resultMsg = new BasicResultMessage();
		String system_url = serverAddress + SYSTEM_REQUEST_URL;

		JSONObject jsonObject = httpRequest(system_url, "PUT", null, false);
		// 如果请求成功
		if (null != jsonObject) {
			try {

				System.out.println("重启服务:" + jsonObject);
				resultMsg.setRet(jsonObject.getInt("ret"));
				resultMsg.setRet_mes(jsonObject.getString("ret_mes"));

			} catch (JSONException e) {

			}
		}

		return resultMsg;
	}

	/*****************************************
	 * 用户登陆
	 *
	 * 
	 * @param name,password
	 * 
	 * @return loginResultMessage 返回登陆信息
	 */
	public LoginResultMessage login(LoginRequestMessage requestMessage) {

		LoginResultMessage resultMsg = new LoginResultMessage();
		String login_url = serverAddress + LOGIN_REQUEST_URL;

		String requestMsg = this.jsonRequestMessage(requestMessage);

		System.out.println("登陆请求message:" + requestMsg);

		JSONObject jsonObject = httpRequest(login_url, "POST", requestMsg, true);
		// 如果请求成功
		if (null != jsonObject) {
			try {

				System.out.println("用户登陆:" + jsonObject);
				resultMsg.setRet(jsonObject.getInt("ret"));
				resultMsg.setRet_mes(jsonObject.getString("ret_mes"));
				resultMsg.setSessionid(jsonObject.getString("sessionid"));
				resultMsg.setVersion(jsonObject.getString("version"));
				resultMsg.setServer_time(jsonObject.getString("server_time"));

				String loginCookie = jsonObject.getString("sessionid");// 登陆时获得的cookie
				if (loginCookie != null && !loginCookie.equals("")) {
					saveCookie(loginCookie);
				}
			} catch (JSONException e) {

			}
		}

		return resultMsg;
	}

	/**
	 * 用户注销
	 * 
	 * 
	 * @return loginResultMessage 返回登陆信息
	 */
	public LoginResultMessage logout() {

		LoginResultMessage resultMsg = new LoginResultMessage();
		String logout_url = serverAddress + LOGOUT_REQUEST_URL;

		JSONObject jsonObject = httpRequest(logout_url, "POST", null, false);
		// 如果请求成功
		if (null != jsonObject) {
			try {

				System.out.println("用户注销:" + jsonObject);
				resultMsg.setRet(jsonObject.getInt("ret"));
				resultMsg.setRet_mes(jsonObject.getString("ret_mes"));

			} catch (JSONException e) {

			}
		}

		return resultMsg;
	}

	/*****************************************
	 * 布控策略 查询布控策略
	 *
	 * 
	 * 
	 * @return policeResultMessage 返回策略信息
	 */
	public PolicyResultMessage getPolices(PolicyRequestMessage requestMessage) {

		PolicyResultMessage resultMsg = new PolicyResultMessage();
		String police_url = serverAddress + POLICY_REQUEST_URL;

		int police_id = requestMessage.getMt_policy_id();

		if (police_id != 0) {
			police_url = setRequestURL(police_url, "police_id", police_id + "");
		}

		JSONObject jsonObject = httpRequest(police_url, "GET", null, false);
		// 如果请求成功
		if (null != jsonObject) {
			try {

				System.out.println("查询布控策略:" + jsonObject);
				resultMsg.setRet(jsonObject.getInt("ret"));
				resultMsg.setMt_policy_num(jsonObject.getInt("mt_policy_num"));
				List<PolicyMessage> list = new ArrayList<PolicyMessage>();
				if (jsonObject.getInt("mt_policy_num") > 0) {
					JSONArray array = jsonObject.getJSONArray("mt_policy_list");
					System.out.println(array);
					list = (List<PolicyMessage>) JSONArray.toCollection(array, PolicyMessage.class);
					Iterator it = list.iterator();
					while (it.hasNext()) {
						PolicyMessage polMsg = (PolicyMessage) it.next();
						System.out.println(polMsg.getMt_policy_id());
					}
				}
				resultMsg.setPolicyMessages(list);
			} catch (JSONException e) {

			}
		}
		return resultMsg;
	}

	/**
	 * 新增布控策略
	 * 
	 * 
	 * @return policyResultMessage 返回策略属性
	 */
	public PolicyResultMessage addPolice(PolicyMessage policyMessage) {

		PolicyResultMessage resultMsg = new PolicyResultMessage();
		String police_url = serverAddress + POLICY_REQUEST_URL;

		String request = this.jsonRequestMessage(policyMessage);

		JSONObject jsonObject = httpRequest(police_url, "PUT", request, false);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				System.out.println("新增布控策略:" + jsonObject);

				resultMsg.setRet(jsonObject.getInt("ret"));
				resultMsg.setRet_mes(jsonObject.getString("ret_mes"));
				resultMsg.setMt_policy_num(jsonObject.getInt("mt_policy_num"));
				List<PolicyMessage> list = new ArrayList<PolicyMessage>();
				PolicyMessage polMsg = new PolicyMessage();
				polMsg.setMt_policy_id(jsonObject.getInt("mt_policy_id"));
				resultMsg.setPolicyMessages(list);
			} catch (JSONException e) {

			}
		}
		return resultMsg;
	}
	
	/**
	 * 删除布控策略
	 * 
	 * 
	 * @return policyResultMessage 返回策略属性
	 */
	public BasicResultMessage deletePolice(PolicyRequestMessage requestMessage) {

		BasicResultMessage resultMsg = new BasicResultMessage();
		String police_url = serverAddress + POLICY_REQUEST_URL+"delete/"+requestMessage.getMt_policy_id();

		String request = this.jsonRequestMessage(requestMessage);

		JSONObject jsonObject = httpRequest(police_url, "DELETE", request, false);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				System.out.println("修改布控策略:" + jsonObject);
				resultMsg.setRet(jsonObject.getInt("ret"));
				resultMsg.setRet_mes(jsonObject.getString("ret_mes"));
			} catch (JSONException e) {

			}
		}
		return resultMsg;
	}

	/**
	 * 修改摄像机
	 * 
	 * 
	 * @return cameraResultMessage 返回摄像机属性
	 */
	public BasicResultMessage updatePolice(PolicyMessage policyMessage) {

		BasicResultMessage resultMsg = new BasicResultMessage();
		String police_url = serverAddress + POLICY_REQUEST_URL+policyMessage.getMt_policy_id();

		String request = this.jsonRequestMessage(policyMessage);

		JSONObject jsonObject = httpRequest(police_url, "POST", request, false);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				System.out.println("修改布控策略:" + jsonObject);
				resultMsg.setRet(jsonObject.getInt("ret"));
				resultMsg.setRet_mes(jsonObject.getString("ret_mes"));
			} catch (JSONException e) {

			}
		}
		return resultMsg;
	}

	/*****************************************
	 * 摄像机 查询摄像机
	 *
	 * 
	 * 
	 * @return cameraResultMessage 返回摄像机信息
	 */
	public CameraResultMessage getCameras(CameraRequestMessage requestMessage) {

		CameraResultMessage resultMsg = new CameraResultMessage();
		String cameras_url = serverAddress + CAMERA_REQUEST_URL;

		int host_id = requestMessage.getHost_id();
		int camera_id = requestMessage.getCamera_id();

		if (host_id != 0) {
			cameras_url = setRequestURL(cameras_url, "host_id", host_id + "");
		}
		if (camera_id != 0) {
			cameras_url = setRequestURL(cameras_url, "camera_id", camera_id + "");
		}

		JSONObject jsonObject = httpRequest(cameras_url, "GET", null, false);
		// 如果请求成功
		if (null != jsonObject) {
			try {

				System.out.println("查询摄像机:" + jsonObject);
				resultMsg.setRet(jsonObject.getInt("ret"));
				resultMsg.setRet_mes(jsonObject.getString("ret_mes"));
				resultMsg.setTotal_count(jsonObject.getInt("total"));
				resultMsg.setList_size(jsonObject.getInt("list_size"));

				List<CameraMessage> list = new ArrayList<CameraMessage>();
				if (jsonObject.getInt("list_size") > 0) {
					JSONArray array = jsonObject.getJSONArray("camera_list");
					System.out.println(array);
					list = (List<CameraMessage>) JSONArray.toCollection(array, CameraMessage.class);
					Iterator it = list.iterator();
					while (it.hasNext()) {
						CameraMessage camMsg = (CameraMessage) it.next();
						System.out.println(camMsg.getCamera_id());
					}

				}
				resultMsg.setCamera_list(list);
			} catch (JSONException e) {

			}
		}

		return resultMsg;
	}

	/**
	 * 新增摄像机
	 * 
	 * 
	 * @return cameraResultMessage 返回摄像机属性
	 */
	public CameraResultMessage addCamera(CameraMessage cameraMessage) {

		CameraResultMessage resultMsg = new CameraResultMessage();
		String cameras_url = serverAddress + CAMERA_REQUEST_URL;

		String requestMsg = this.jsonRequestMessage(cameraMessage);

		System.out.println("新增摄像机message:" + requestMsg);

		JSONObject jsonObject = httpRequest(cameras_url, "PUT", requestMsg, false);
		// 如果请求成功
		if (null != jsonObject) {
			try {

				System.out.println("新增摄像机:" + jsonObject);
				resultMsg.setRet(jsonObject.getInt("ret"));
				resultMsg.setRet_mes(jsonObject.getString("ret_mes"));

				ArrayList<CameraMessage> list = new ArrayList<CameraMessage>();
				CameraMessage camera = new CameraMessage();
				camera.setCamera_id(jsonObject.getInt("camera_id"));
				camera.setCamera_state(jsonObject.getInt("camera_state"));
				list.add(camera);
				resultMsg.setCamera_list(list);
			} catch (JSONException e) {

			}
		}

		return resultMsg;
	}

	/**
	 * 配置摄像机
	 * 
	 * 
	 * @return cameraResultMessage 返回摄像机属性
	 */
	public CameraResultMessage updateCamera(CameraMessage cameraMessage) {

		CameraResultMessage resultMsg = new CameraResultMessage();
		String cameras_url = serverAddress + CAMERA_REQUEST_URL + "/" + cameraMessage.getCamera_id();

		String requestMsg = this.jsonRequestMessage(cameraMessage);

		System.out.println("配置相机请求message:" + requestMsg);

		JSONObject jsonObject = httpRequest(cameras_url, "PUT", requestMsg, false);
		// 如果请求成功
		if (null != jsonObject) {
			try {

				System.out.println("配置摄像机:" + jsonObject);
				resultMsg.setRet(jsonObject.getInt("ret"));
				resultMsg.setRet_mes(jsonObject.getString("ret_mes"));

				ArrayList<CameraMessage> list = new ArrayList<CameraMessage>();
				CameraMessage camera = new CameraMessage();
				camera.setCamera_id(jsonObject.getInt("camera_id"));
				camera.setCamera_state(jsonObject.getInt("camera_state"));
				list.add(camera);
				resultMsg.setCamera_list(list);
			} catch (JSONException e) {

			}
		}

		return resultMsg;
	}

	/**
	 * 删除摄像机
	 * 
	 * 
	 * @return cameraResultMessage 返回摄像机属性
	 */
	public CameraResultMessage deleteCamera(int camera_id) {

		CameraResultMessage resultMsg = new CameraResultMessage();
		String cameras_url = serverAddress + CAMERA_REQUEST_URL + "/" + camera_id;

		JSONObject jsonObject = httpRequest(cameras_url, "DELETE", null, false);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				System.out.println("删除摄像机:" + jsonObject);
				resultMsg.setRet(jsonObject.getInt("ret"));
				resultMsg.setRet_mes(jsonObject.getString("ret_mes"));
			} catch (JSONException e) {

			}
		}

		return resultMsg;
	}

	/**
	 * 告警抓拍统计数据清零
	 * 
	 * 
	 * @return cameraResultMessage 返回摄像机属性
	 */
	public CameraResultMessage statreset(int camera_id) {

		CameraResultMessage resultMsg = new CameraResultMessage();
		String cameras_url = serverAddress + CAMERA_REQUEST_URL + "/" + camera_id + "/statreset";

		JSONObject jsonObject = httpRequest(cameras_url, "POST", null, false);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				System.out.println("告警抓拍统计数据清零:" + jsonObject);
				resultMsg.setRet(jsonObject.getInt("ret"));
				resultMsg.setRet_mes(jsonObject.getString("ret_mes"));
				ArrayList<CameraMessage> list = new ArrayList<CameraMessage>();
				CameraMessage camera = new CameraMessage();
				camera.setLast_clear_time(jsonObject.getString("last_clear_time"));
				list.add(camera);
				resultMsg.setCamera_list(list);
			} catch (JSONException e) {
			}
		}

		return resultMsg;
	}

	/**
	 * 开始人脸布控
	 * 
	 * 
	 * @return cameraResultMessage 返回摄像机属性
	 */
	public CameraResultMessage recognition(int camera_id) {

		CameraResultMessage resultMsg = new CameraResultMessage();
		String cameras_url = serverAddress + CAMERA_REQUEST_URL + "/video_recognition/" + camera_id;

		JSONObject jsonObject = httpRequest(cameras_url, "POST", null, false);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				System.out.println("开始人脸布控:" + jsonObject);
				resultMsg.setRet(jsonObject.getInt("ret"));
				resultMsg.setRet_mes(jsonObject.getString("ret_mes"));
				ArrayList<CameraMessage> list = new ArrayList<CameraMessage>();
				CameraMessage camera = new CameraMessage();
				camera.setCamera_id(jsonObject.getInt("camera_id"));
				camera.setCamera_state(jsonObject.getInt("camera_state"));
				list.add(camera);
				resultMsg.setCamera_list(list);
			} catch (JSONException e) {
			}
		}

		return resultMsg;
	}

	/**
	 * 停止人脸布控
	 * 
	 * 
	 * @return cameraResultMessage 返回摄像机属性
	 */
	public CameraResultMessage recognitionStop(int camera_id) {

		CameraResultMessage resultMsg = new CameraResultMessage();
		String cameras_url = serverAddress + CAMERA_REQUEST_URL + "/video_recognition_stop/" + camera_id;

		JSONObject jsonObject = httpRequest(cameras_url, "POST", null, false);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				System.out.println("停止人脸布控:" + jsonObject);
				resultMsg.setRet(jsonObject.getInt("ret"));
				resultMsg.setRet_mes(jsonObject.getString("ret_mes"));
				ArrayList<CameraMessage> list = new ArrayList<CameraMessage>();
				CameraMessage camera = new CameraMessage();
				camera.setCamera_state(jsonObject.getInt("camera_state"));
				list.add(camera);
				resultMsg.setCamera_list(list);
			} catch (JSONException e) {
			}
		}

		return resultMsg;
	}

	/*****************************************
	 * 人脸库 查询人脸库
	 *
	 *
	 * 
	 * 
	 * @return faceDBResultMessage 返回数据库属性
	 */
	public FaceDBResultMessage getFaceDBs(FaceDBRequestMessage requestMessage) {

		FaceDBResultMessage resultMsg = new FaceDBResultMessage();
		String faceDB_url = serverAddress + FACEDB_REQUEST_URL;

		String dbname = requestMessage.getDbname();

		if (dbname != null && !dbname.replace(" ", "").equals("")) {
			faceDB_url = setRequestURL(faceDB_url, "dbname", dbname);

		}

		JSONObject jsonObject = httpRequest(faceDB_url, "GET", null, false);
		// 如果请求成功
		if (null != jsonObject) {
			try {

				System.out.println("查询人脸库：" + jsonObject);
				resultMsg.setRet(jsonObject.getInt("ret"));
				resultMsg.setRet_mes(jsonObject.getString("ret_mes"));
				resultMsg.setTotal_count(jsonObject.getInt("total_count"));
				resultMsg.setList_size(jsonObject.getInt("list_size"));

				List<FaceDBMessage> list = new ArrayList<FaceDBMessage>();
				if (jsonObject.getInt("list_size") > 0) {
					JSONArray array = jsonObject.getJSONArray("facedb_list");
					System.out.println(array);
					list = (List<FaceDBMessage>) JSONArray.toCollection(array, FaceDBMessage.class);
					Iterator it = list.iterator();
					while (it.hasNext()) {
						FaceDBMessage faceMsg = (FaceDBMessage) it.next();
						System.out.println(faceMsg.getDb_id());
					}

				}
				resultMsg.setFacedb_list(list);
			} catch (JSONException e) {
			}
		}

		return resultMsg;
	}

	/*****************************************
	 * 告警记录 查询告警记录
	 *
	 * 
	 * 
	 * @return alarmResultMessage 查询的告警记录
	 */
	public AlarmResultMessage getAlarms(AlarmRequestMessage requestMessage) {

		AlarmResultMessage resultMsg = new AlarmResultMessage();
		String alarm_url = serverAddress + ALARM_REQUEST_URL;

		String alarm_id = requestMessage.getAlarm_id();
		String camera_id_list = requestMessage.getCamera_id_list();
		int alarm_type = requestMessage.getAlarm_type();

		if (alarm_id != null && !alarm_id.replace(" ", "").equals("")) {
			alarm_url = setRequestURL(alarm_url, "alarm_id", alarm_id);
		}
		if (camera_id_list != null && !camera_id_list.replace(" ", "").equals("")) {
			alarm_url = setRequestURL(alarm_url, "camera_id_list", camera_id_list);
		}
		alarm_url = setRequestURL(alarm_url, "alarm_type", alarm_type+"");
		
		JSONObject jsonObject = httpRequest(alarm_url, "GET", null, false);
		// 如果请求成功
		if (null != jsonObject) {
			try {

				System.out.println("查询告警记录:" + jsonObject);
				resultMsg.setRet(jsonObject.getInt("ret"));
				resultMsg.setRet_mes(jsonObject.getString("ret_mes"));
				resultMsg.setAlarm_size(jsonObject.getInt("alarm_size"));

				List<AlarmMessage> list = new ArrayList<AlarmMessage>();
				if (jsonObject.getInt("alarm_size") > 0) {
					JSONArray array = jsonObject.getJSONArray("alarm_list");
					for (int i = 0; i < array.size(); i++) {
						JSONObject object = (JSONObject) array.get(i);
						System.out.println("object:" + object);
						AlarmMessage alarmMessage = new AlarmMessage();
						List<SearchMessage> searchMessages = new ArrayList<SearchMessage>();
						if (object.getJSONArray("search_list") != null) {
							searchMessages = (List<SearchMessage>) JSONArray
									.toCollection(object.getJSONArray("search_list"), SearchMessage.class);
							alarmMessage.setSearchMessages(searchMessages);
						}
						alarmMessage.setAlarm_id(object.getString("alarm_id"));
						alarmMessage.setCamera_id(object.getInt("camera_id"));
						alarmMessage.setCamera_name(object.getString("camera_name"));
						alarmMessage.setAlarm_time(object.getString("alarm_time"));
						alarmMessage.setMonitor_type(object.getInt("monitor_type"));
						alarmMessage.setAlarm_type(object.getInt("alarm_type"));
						alarmMessage.setPhoto_name(object.getString("photo_name"));
						alarmMessage.setFace_blur(object.getInt("face_blur"));
						alarmMessage.setPhoto_host_id(object.getInt("photo_host_id"));
						alarmMessage.setState(object.getInt("state"));

						list.add(alarmMessage);
					}
					Iterator it = list.iterator();
					while (it.hasNext()) {
						AlarmMessage alarmMsg = (AlarmMessage) it.next();
						System.out.println("查询：" + alarmMsg.getSearchMessages());
					}

				}
				resultMsg.setAlarmMessages(list);
			} catch (JSONException e) {

			}
		}

		return resultMsg;
	}

	/**
	 * 删除告警记录
	 * 
	 * 
	 * @return alarmResultMessage 告警信息
	 */
	public AlarmResultMessage deleteAlarms(AlarmRequestMessage requestMessage) {

		AlarmResultMessage resultMsg = new AlarmResultMessage();
		String alarm_url = serverAddress + ALARM_REQUEST_URL;

		String alarm_id = requestMessage.getAlarm_id();
		String camera_id_list = requestMessage.getCamera_id_list();

		if (alarm_id != null && !alarm_id.replace(" ", "").equals("")) {
			alarm_url = setRequestURL(alarm_url, "alarm_id", alarm_id);
		}
		if (camera_id_list != null && !camera_id_list.replace(" ", "").equals("")) {
			alarm_url = setRequestURL(alarm_url, "camera_id_list", camera_id_list);
		}
		JSONObject jsonObject = httpRequest(alarm_url, "DELETE", null, false);
		// 如果请求成功
		if (null != jsonObject) {
			try {

				System.out.println("删除告警记录:" + jsonObject);
				resultMsg.setRet(jsonObject.getInt("ret"));
				resultMsg.setRet_mes(jsonObject.getString("ret_mes"));
				resultMsg.setDelete_result(jsonObject.getInt("delete_result"));

			} catch (JSONException e) {

			}
		}

		return resultMsg;
	}

	/*****************************************
	 * 告警记录中图片信息
	 *
	 * 
	 * 
	 * @return imageResultMessage 获得告警记录中的现场抓拍图片
	 */
	public ImageResultMessage getImage(ImageRequestMessage requestMessage) {

		ImageResultMessage resultMsg = new ImageResultMessage();
		String image_url = serverAddress + IMAGE_REQUEST_URL + requestMessage.getPhoto_host_id();

		String filename = requestMessage.getFilename();

		if (filename != null && !filename.replace(" ", "").equals("")) {
			image_url = setRequestURL(image_url, "filename", filename);
		}

		JSONObject jsonObject = httpRequest(image_url, "GET", null, false);
		// 如果请求成功
		if (null != jsonObject) {
			try {

				System.out.println("删除告警记录:" + jsonObject);
				resultMsg.setRet(jsonObject.getInt("ret"));
				resultMsg.setRet_mes(jsonObject.getString("ret_mes"));

				JSONObject object = jsonObject.getJSONObject("image_data");

				ImageMessage imageMessage = (ImageMessage) JSONObject.toBean(object, ImageMessage.class);

				resultMsg.setImage(imageMessage);

			} catch (JSONException e) {

			}
		}

		return resultMsg;
	}

	/**
	 * 发起http请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST、PUT、DELETE、HEAD、TRACE 、TRACE、OPTIONS ）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr, Boolean isLogin) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {

			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();

			httpUrlConn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);

			if (!isLogin) {
				String cookie = checkCookie();// 检查cookie
				if (!cookie.equals("")) {
					httpUrlConn.setRequestProperty("Cookie", cookie);
				}
			}

			if ("GET".equalsIgnoreCase(requestMethod)) {
				httpUrlConn.connect();
			}

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();// 此处getOutputStream会隐含的进行connect(即：如同调用上面的connect()方法，
				// 注意编码格式，防止中文乱码
				if (outputStream != null) {
					outputStream.write(outputStr.getBytes("UTF-8"));
					outputStream.flush();
					outputStream.close();
				}

			}

			if (httpUrlConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				// 将返回的输入流转换成字符串
				InputStream inputStream = httpUrlConn.getInputStream();// <===注意，实际发送请求的代码段就在这里
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

				if (!buffer.toString().replace(" ", "").equals(""))
					jsonObject = JSONObject.fromObject(buffer.toString());
			}

			Map<String, List<String>> maps = httpUrlConn.getHeaderFields();
			for (String key : maps.keySet()) {
				System.out.println(key + ":" + maps.get(key));
			}

			if (httpUrlConn != null)
				httpUrlConn.disconnect();

		} catch (ConnectException ce) {
			log.error(" server connection timed out.");
		} catch (Exception e) {
			log.error("http request error:{}", e);
		}
		return jsonObject;
	}

	/*
	 * json转换请求对象
	 */
	public String jsonRequestMessage(Object object) {
		JsonConfig jsonConfig = new JsonConfig();
		PropertyFilter filter = new PropertyFilter() {
			public boolean apply(Object object, String fieldName, Object fieldValue) {
				if (fieldValue instanceof List) {
					List<Object> list = (List<Object>) fieldValue;
					if (list.size() == 0) {
						return true;
					}
				}
				if (fieldName.equals("camera_id") || fieldName.equals("skip") || fieldName.equals("top")) {
					return null == fieldValue || "".equals(fieldValue) || fieldValue.equals(0);
				} else {
					return null == fieldValue || "".equals(fieldValue);
				}
			}
		};
		jsonConfig.setJsonPropertyFilter(filter);

		JSONObject jsonObject = JSONObject.fromObject(object, jsonConfig);
		return jsonObject.toString();
	}

	public void saveCookie(String cookie) {
		try {
			List<Face> faces = faceService.findFaceList();
			if (faces != null && faces.size() > 0) {
				Face face = faces.get(0);
				face.setCookie(cookie);
				faceService.updateFace(face.getId(), face);
			} else {

				Face face = new Face();
				face.setCookie(cookie);
				faceService.saveFace(face);
			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public String checkCookie() {
		String cookie = "";
		try {
			List<Face> faces = faceService.findFaceList();
			if (faces != null && faces.size() > 0) {
				cookie = faces.get(0).getCookie();
				System.out.println(cookie);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cookie;
	}

	public String setRequestURL(String url, String parmName, String parmValue) {
		String sign = setRequestSign(url);
		url = url + sign + parmName + "=" + parmValue;
		return url;
	}

	public String setRequestSign(String url) {
		if (url.contains("?")) {
			return "&";
		}
		return "?";
	}

	public String getServerAddress() {
		return serverAddress;
	}

	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}

}
