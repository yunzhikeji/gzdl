package com.yz.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public String addCameraToCloud(Integer id) throws Exception {
		LoginResultMessage resultMessage = login();
		if (resultMessage.getRet() == 0) {
			CameraMessage cameraMessage = new CameraMessage();
			cameraMessage.setCamera_name("224");
			cameraMessage.setCamera_mode(0);
			cameraMessage.setUrl("rtsp://admin:admin@192.168.1.224:554");
			cameraMessage.setDb_id_list("7");
			cameraMessage.setNode_id(0);
			cameraMessage.setFixed_host(0);
			cameraMessage.setMt_policy_id(1);

			CameraResultMessage cameraResultMessage = requestService.addCamera(cameraMessage);

			if (cameraResultMessage.getRet() == 0) {
				int cameraid = cameraResultMessage.getCamera_list().get(0).getCamera_id();
				CameraResultMessage cameraResulttMessage = requestService.recognition(cameraid);
				if (cameraResulttMessage.getRet() == 0) {
					CameraCustom cameraCustom = new CameraCustom();
					cameraCustom.setCameraid(cameraid); // 布控成功将cameraid保存到数据库
					cameraCustom.setStat(1); // 布控成功就将stat设为1，否则为0
					cameraCustom.setIscontroll(1); // 布控成功将iscontroller设为1，否则为0
					cameraService.updateCamera(id, cameraCustom);

					// AlarmRequestMessage alarmRequestMessage = new
					// AlarmRequestMessage();
					// alarmRequestMessage.setCamera_id_list(cameraResulttMessage.getCamera_list().get(0).getCamera_id()+"");
					// AlarmResultMessage alarmResultMessage =
					// requestService.getAlarms(alarmRequestMessage);

				}

			}
			return "布控成功";

		} else
			return "布控失败";
	}

//	@RequestMapping("/getalarms")
//	public @ResponseBody List<AlarmMessage> getAlarms(Integer id) throws Exception {
//
//		Camera camera = cameraService.findCameraById(id);
//
//		AlarmRequestMessage alarmRequestMessage = new AlarmRequestMessage();
//		alarmRequestMessage.setCamera_id_list(camera.getCameraid() + "");
//		AlarmResultMessage alarmResultMessage = requestService.getAlarms(alarmRequestMessage);
//		return alarmResultMessage.getAlarmMessages();

//	}

	@RequestMapping("/login")
	public @ResponseBody LoginResultMessage login() throws Exception {

		LoginRequestMessage requestMessage  = new LoginRequestMessage();
		if (faceUser != null) {
			requestMessage.setUser_name(faceUser.getUsername());;
			requestMessage.setUser_pwd(MD5Util.getMD5(faceUser.getPassword()));
			requestMessage.setModel(faceUser.getMode());
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
	public @ResponseBody CameraResultMessage deleteCamera() throws Exception {

		int camera_id = 0;
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
	public @ResponseBody List<AlarmMessage> getAlarms(Integer id) throws Exception {
		Camera camera = cameraService.findCameraById(id);
		
		AlarmRequestMessage requestMessage = new AlarmRequestMessage();
		requestMessage.setCamera_id_list(camera.getCameraid() + "");

		return requestService.getAlarms(requestMessage).getAlarmMessages();

	}

	@RequestMapping("/deleteAlarms")
	public @ResponseBody AlarmResultMessage deleteAlarms() throws Exception {
		AlarmRequestMessage alarmRequestMessage = new AlarmRequestMessage();
		alarmRequestMessage.setAlarm_id("1+2");
		return requestService.deleteAlarms(alarmRequestMessage);
	}

}
