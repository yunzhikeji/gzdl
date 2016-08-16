package com.yz.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yz.exception.CustomException;
import com.yz.facecloud.model.AlarmMessage;
import com.yz.facecloud.model.AlarmRequestMessage;
import com.yz.facecloud.model.AlarmResultMessage;
import com.yz.facecloud.model.CameraMessage;
import com.yz.facecloud.model.CameraRequestMessage;
import com.yz.facecloud.model.CameraResultMessage;
import com.yz.facecloud.model.FaceDBRequestMessage;
import com.yz.facecloud.model.FaceDBResultMessage;
import com.yz.facecloud.model.LoginResultMessage;
import com.yz.facecloud.model.PolicyRequestMessage;
import com.yz.facecloud.model.PolicyResultMessage;
import com.yz.facecloud.model.FaceUser;
import com.yz.facecloud.model.LoginRequestMessage;
import com.yz.facecloud.service.HttpRequestService;
import com.yz.facecloud.util.MD5Util;
import com.yz.po.Camera;
import com.yz.po.CameraCustom;
import com.yz.service.CameraService;

@Controller
@RequestMapping("/facecloud")
public class FacecloudController {

	@Autowired
	private HttpRequestService requestService;

	@Autowired
	private CameraService cameraService;

	@Resource(name = "faceUser")
	private FaceUser faceUser;

	/*
	 * 经测试 reboot接口无效
	 * 
	 * @RequestMapping("/reboot") public @ResponseBody BasicResultMessage
	 * reboot() throws Exception {
	 * 
	 * return requestService.reboot(); }
	 */

	@RequestMapping("/addcameratocloud")
	public @ResponseBody String addCameraToCloud(Integer id) throws Exception {

		int cameraid = 0;
		Camera camera = cameraService.findCameraById(id);

		CameraResultMessage cameraResultMessage = new CameraResultMessage();

		CameraMessage cameraMessage = new CameraMessage();
		cameraMessage.setCamera_name(camera.getSipid());
		cameraMessage.setCamera_mode(0);
		cameraMessage.setUrl(this.setRtspURL(camera.getSipid()));
		cameraMessage.setDb_id_list("7"); // 人脸库
		cameraMessage.setNode_id(0);
		cameraMessage.setFixed_host(0);
		cameraMessage.setMt_policy_id(1); // 策略
		if (camera.getCameraid() == null||camera.getCameraid() == 0) {
			cameraResultMessage = requestService.addCamera(cameraMessage);

			if (!checkLoginState(cameraResultMessage.getRet())) {
				setLoginState();
				cameraResultMessage = requestService.addCamera(cameraMessage);
			}

			cameraid = cameraResultMessage.getCamera_list().get(0).getCamera_id();
		} else {
			cameraid = camera.getCameraid();
		}

		CameraResultMessage cameraResulttMessage = requestService.recognition(cameraid);// 布控下发

		this.updateCamera(id, cameraid);

		if (cameraResulttMessage.getRet() == 0) {
			return "布控成功";
		}
		return "布控失败：" + cameraResulttMessage.getRet_mes();
	}

	@RequestMapping("/getalarms")
	public @ResponseBody List<AlarmMessage> getAlarms(Integer id) throws Exception {

		Camera camera = cameraService.findCameraById(id);

		if (camera.getCameraid() != null&&camera.getCameraid() != 0) {
			AlarmRequestMessage alarmRequestMessage = new AlarmRequestMessage();
			alarmRequestMessage.setCamera_id_list(camera.getCameraid() + "");

			AlarmResultMessage alarmResultMessage = requestService.getAlarms(alarmRequestMessage);

			if (!checkLoginState(alarmResultMessage.getRet())) {
				setLoginState();
				alarmResultMessage = requestService.getAlarms(alarmRequestMessage);
			}
			return alarmResultMessage.getAlarmMessages();
		} else {
			return null;
		}

	}

	@RequestMapping("/login")
	public @ResponseBody LoginResultMessage login() throws Exception {

		LoginRequestMessage requestMessage = new LoginRequestMessage();
		if (faceUser != null) {
			requestMessage.setUser_name(faceUser.getUsername());
			;
			requestMessage.setUser_pwd(MD5Util.getMD5(faceUser.getPassword()));
			requestMessage.setMode(faceUser.getMode());
		}

		return requestService.login(requestMessage);
	}

	@RequestMapping("/logout")
	public @ResponseBody LoginResultMessage logout() throws Exception {
		return requestService.logout();
	}

	@RequestMapping("/policies")
	public @ResponseBody PolicyResultMessage policies() throws Exception {

		PolicyRequestMessage requestMessage = new PolicyRequestMessage();
		return requestService.getPolices(requestMessage);
	}

	@RequestMapping("/cameras")
	public @ResponseBody CameraResultMessage cameras() throws Exception {
		CameraRequestMessage requestMessage = new CameraRequestMessage();
		return requestService.getCameras(requestMessage);
	}

	@RequestMapping("/addCamera")
	public @ResponseBody CameraResultMessage addCamera() throws Exception {

		CameraMessage cameraMessage = new CameraMessage();

		cameraMessage.setCamera_name("cccc");
		cameraMessage.setCamera_mode(0);
		cameraMessage.setUrl("rtsp://admin:admin@192.168.1.224:554");
		cameraMessage.setDb_id_list("7");
		cameraMessage.setNode_id(0);
		cameraMessage.setFixed_host(0);
		cameraMessage.setMt_policy_id(1);

		return requestService.addCamera(cameraMessage);
	}

	@RequestMapping("/updateCamera")
	public @ResponseBody CameraResultMessage updateCamera() throws Exception {

		CameraMessage cameraMessage = new CameraMessage();

		cameraMessage.setCamera_id(1);
		cameraMessage.setCamera_name("aaa");
		cameraMessage.setCamera_mode(0);
		cameraMessage.setUrl("rtsp://admin:12345@192.168.0.64/h264/ch1/main/av_stream");
		cameraMessage.setDb_id_list("2+4+8");
		// cameraMessage.setNode_id(11);
		// cameraMessage.setFixed_host(1);
		cameraMessage.setMt_policy_id(1);

		return requestService.addCamera(cameraMessage);
	}

	@RequestMapping("/deleteCamera")
	public @ResponseBody CameraResultMessage deleteCamera(int id) throws Exception {

		int camera_id = 0;
		// 删除时设置camera_id为0
		Camera camera = cameraService.findCameraById(id);
		camera_id = camera.getCameraid();
		camera.setCameraid(0);
		cameraService.updateCamera(id,camera);
		return requestService.deleteCamera(camera_id);
	}

	@RequestMapping("/recognition")
	public @ResponseBody CameraResultMessage recognition() throws Exception {

		int camera_id = 37;
		return requestService.recognition(camera_id);
	}

	@RequestMapping("/recognitionStop")
	public @ResponseBody CameraResultMessage recognitionStop() throws Exception {

		int camera_id = 37;
		return requestService.recognitionStop(37);
	}

	@RequestMapping("/statreset")
	public @ResponseBody CameraResultMessage statreset() throws Exception {

		int camera_id = 1;
		return requestService.statreset(1);
	}

	@RequestMapping("/faceDBs")
	public @ResponseBody FaceDBResultMessage faceDBs() throws Exception {

		FaceDBRequestMessage requestMessage = new FaceDBRequestMessage();
		return requestService.getFaceDBs(requestMessage);
	}

	@RequestMapping("/alarms")
	public @ResponseBody AlarmResultMessage getAlarms() throws Exception {

		AlarmRequestMessage requestMessage = new AlarmRequestMessage();
		return requestService.getAlarms(requestMessage);

	}

	@RequestMapping("/deleteAlarms")
	public @ResponseBody AlarmResultMessage deleteAlarms() throws Exception {
		AlarmRequestMessage alarmRequestMessage = new AlarmRequestMessage();
		alarmRequestMessage.setAlarm_id("1+2");
		return requestService.deleteAlarms(alarmRequestMessage);
	}

	/*********************************
	 * 简单方法封装
	 * 
	 * 本地数据库操作
	 * 
	 * 保存 人脸服务器胡摄像机id
	 */
	public void updateCamera(Integer id, int cameraid) throws Exception {
		// TODO Auto-generated method stub
		CameraCustom cameraCustom = new CameraCustom();
		cameraCustom.setCameraid(cameraid); // 布控成功将cameraid保存到数据库
		cameraCustom.setStat(1); // 布控成功就将stat设为1，否则为0
		cameraCustom.setIscontroll(1); // 布控成功将iscontroller设为1，否则为0
		cameraService.updateCamera(id, cameraCustom);

	}

	/*
	 * 拼装 远程流媒体服务器rtsp流地址
	 */
	public String setRtspURL(String sipid) {
		if (sipid != null) {
			String url = "rtsp://127.0.0.1:8554/" + sipid + "/1";
			return url;
		}
		return null;
	}

	/*
	 * 检查登录状态
	 */
	public boolean checkLoginState(int ret) {
		if (ret == 0) {
			return true;
		} else {
			return false;
		}

	}

	/*
	 * 获取登录cookie
	 */
	public void setLoginState() {

		int result = 1;
		int counter = 0;
		while (true) {
			try {
				result = this.login().getRet();
				counter++;
				if(counter==15)
				{
					CustomException exception = new CustomException("人脸服务器连接异常。");
					throw exception;
				}
				if (result == 0 || result == 4011)// 登录成功 请求超时
																	// camera_id设置错误
					break;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
