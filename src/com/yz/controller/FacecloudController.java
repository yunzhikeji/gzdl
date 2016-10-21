package com.yz.controller;

import java.util.ArrayList;
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
import com.yz.facecloud.model.FaceUser;
import com.yz.facecloud.model.LoginRequestMessage;
import com.yz.facecloud.model.LoginResultMessage;
import com.yz.facecloud.model.PolicyRequestMessage;
import com.yz.facecloud.model.PolicyResultMessage;
import com.yz.facecloud.service.FaceCameraService;
import com.yz.facecloud.service.HttpRequestService;
import com.yz.facecloud.util.MD5Util;
import com.yz.facecloud.vo.AlarmMessageVO;
import com.yz.model.CameraServer;
import com.yz.po.Camera;
import com.yz.po.CameraCustom;
import com.yz.service.CameraService;
import com.yz.utils.DateTimeKit;

@Controller
@RequestMapping("/facecloud")
public class FacecloudController {

	@Autowired
	private HttpRequestService requestService;

	@Autowired
	private CameraService cameraService;
	
	@Autowired
	private FaceCameraService faceCameraService;

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
		
		if(camera==null)
		{
			return "没有当前设备";
		}

		CameraResultMessage cameraResultMessage = new CameraResultMessage();
		
		//判断当前设备是否已经添加到人脸服务器
		if (camera.getCameraid() == null||camera.getCameraid() == 0) {
			CameraMessage cameraMessage = new CameraMessage();
			
			cameraMessage.setCamera_name(camera.getCnumber()+"_"+DateTimeKit.getLocalTime());
			cameraMessage.setCamera_mode(0);
			if(faceCameraService.setRtspURL(camera.getSipid())==null)
			{
				return "摄像头rtsp流配置错误,请确保系统rtsp服务地址配置正确";
			}
			cameraMessage.setUrl(faceCameraService.setRtspURL(camera.getSipid()));
			cameraMessage.setDb_id_list("1"); // 人脸库
			cameraMessage.setNode_id(0);
			cameraMessage.setFixed_host(0);
			cameraMessage.setMt_policy_id(1); // 策略
			
			
			cameraResultMessage = requestService.addCamera(cameraMessage);

			if (!faceCameraService.checkLoginState(cameraResultMessage.getRet())) {
				setLoginState();
				cameraResultMessage = requestService.addCamera(cameraMessage);
			}
			
			if(cameraResultMessage.getCamera_list()!=null&&cameraResultMessage.getCamera_list().size()>0)
			{
				cameraid = cameraResultMessage.getCamera_list().get(0).getCamera_id();
			}
		} else {
			cameraid = camera.getCameraid();
		}

		if(cameraid==0)
		{
			return "布控失败:无法获取人脸服务器中摄像头对应的cameraid.";
		}
		
		if(faceCameraService.checkCameraOnFaceServer(cameraid)==null)
		{
			faceCameraService.updateCamera(id, 0,false);//布控成功修改数据库
			return "布控失败,人脸服务器中暂无当前设备.";
		}
		
		if(!faceCameraService.checkCameraStateOnFaceServer(cameraid))
		{
			cameraResultMessage = requestService.recognition(cameraid);// 布控下发
		}

		if (cameraResultMessage.getRet() == 0) {
			faceCameraService.updateCamera(id, cameraid,true);//布控成功修改数据库
			return "布控成功";
		}else
		{
			faceCameraService.updateCamera(id, cameraid,false);//布控成功修改数据库
			return "布控失败：" + cameraResultMessage.getRet_mes();
		}
		
	}


	@RequestMapping("/getalarms")
	public @ResponseBody List<AlarmMessage> getAlarms(Integer id) throws Exception {

		Camera camera = cameraService.findCameraById(id);

		if (camera.getCameraid() != null&&camera.getCameraid() != 0) {
			AlarmRequestMessage alarmRequestMessage = new AlarmRequestMessage();
			alarmRequestMessage.setCamera_id_list(camera.getCameraid() + "");

			AlarmResultMessage alarmResultMessage = requestService.getAlarms(alarmRequestMessage);

			if (!faceCameraService.checkLoginState(alarmResultMessage.getRet())) {
				setLoginState();
				alarmResultMessage = requestService.getAlarms(alarmRequestMessage);
			}
			return alarmResultMessage.getAlarmMessages();
		} else {
			return null;
		}

	}
	
	
	
	@RequestMapping("/getalarmvos")
	public @ResponseBody List<AlarmMessageVO> getAlarmVOs(Integer id) throws Exception {

		Camera camera = cameraService.findCameraById(id);

		if (camera!=null&&camera.getCameraid() != null&&camera.getCameraid() != 0) {
			AlarmRequestMessage alarmRequestMessage = new AlarmRequestMessage();
			alarmRequestMessage.setCamera_id_list(camera.getCameraid() + "");
			alarmRequestMessage.setAlarm_type(9);

			AlarmResultMessage alarmResultMessage = requestService.getAlarms(alarmRequestMessage);

			if (!faceCameraService.checkLoginState(alarmResultMessage.getRet())) {
				setLoginState();
				alarmResultMessage = requestService.getAlarms(alarmRequestMessage);
			}
			
			List<AlarmMessageVO> alarmMessageVOs = new ArrayList<AlarmMessageVO>();
			if(alarmResultMessage!=null&&alarmResultMessage.getAlarmMessages()!=null)
			{
				for(int i=0;i<alarmResultMessage.getAlarmMessages().size();i++)
				{
					AlarmMessage alarm = alarmResultMessage.getAlarmMessages().get(i);
					
					//根据布控类型来获取，当前布控类型为白名单+抓拍，所以获取告警记录时，仅需获取白名单和抓拍即可~
					if(alarm.getAlarm_type()!=1)
					{
						AlarmMessageVO vo = new AlarmMessageVO();
						vo.setAlarm_id(alarm.getAlarm_id());
						vo.setAlarm_time(alarm.getAlarm_time());
						vo.setCamera_name(alarm.getCamera_name());
						//// 0,抓拍无告警   1,黑名单告警2,白名单告警
						switch (alarm.getAlarm_type()) {
						case 0:
							vo.setAlarm_typename("工作人员");
							break;
						case 1:
							vo.setAlarm_typename("黑名单");
							break;
						case 2:
							vo.setAlarm_typename("非工作人员");
							break;
						default:
							break;
						}
						/*if(alarm.getSearchMessages()!=null)
						{
							for(int j=0;j<alarm.getSearchMessages().size();j++)
							{
								SearchMessage message = alarm.getSearchMessages().get(0);
								if(message.getPerson_name()!=null&&!message.getPerson_name().equals(""))
								{
									vo.setPhoto_name(message.getPerson_name());
									break;
								}
							}
						}else
						{
							vo.setPhoto_name("未登记姓名");
						}*/
						alarmMessageVOs.add(vo);
					}
					
				}
			}
			return alarmMessageVOs;
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
		requestMessage.setAlarm_type(9);
		return requestService.getAlarms(requestMessage);

	}

	@RequestMapping("/deleteAlarms")
	public @ResponseBody AlarmResultMessage deleteAlarms() throws Exception {
		AlarmRequestMessage alarmRequestMessage = new AlarmRequestMessage();
		alarmRequestMessage.setAlarm_id("1+2");
		return requestService.deleteAlarms(alarmRequestMessage);
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
				if (result == 0 || result == 4011)// 登录成功 请求超时
				{
					// camera_id设置错误
					break;
				}
					
				counter++;
				if(counter==15)
				{
					CustomException exception = new CustomException("人脸服务器连接异常。");
					throw exception;
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}


}
