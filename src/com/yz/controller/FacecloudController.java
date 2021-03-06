package com.yz.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yz.facecloud.model.AlarmMessage;
import com.yz.facecloud.model.AlarmRequestMessage;
import com.yz.facecloud.model.AlarmResultMessage;
import com.yz.facecloud.model.CameraMessage;
import com.yz.facecloud.model.CameraRequestMessage;
import com.yz.facecloud.model.CameraResultMessage;
import com.yz.facecloud.model.FaceDBRequestMessage;
import com.yz.facecloud.model.FaceDBResultMessage;
import com.yz.facecloud.model.FaceDataResultMessage;
import com.yz.facecloud.model.FaceMessage;
import com.yz.facecloud.model.FaceRequestMessage;
import com.yz.facecloud.model.FaceResultMessage;
import com.yz.facecloud.model.ImageMessage;
import com.yz.facecloud.model.ImageRequestMessage;
import com.yz.facecloud.model.ImageResultMessage;
import com.yz.facecloud.model.LoginResultMessage;
import com.yz.facecloud.model.PolicyRequestMessage;
import com.yz.facecloud.model.PolicyResultMessage;
import com.yz.facecloud.model.SearchMessage;
import com.yz.facecloud.service.FaceCameraService;
import com.yz.facecloud.service.FaceImageService;
import com.yz.facecloud.service.HttpRequestService;
import com.yz.facecloud.vo.AlarmMessageVO;
import com.yz.model.AjaxMessage;
import com.yz.po.Alarm;
import com.yz.po.Camera;
import com.yz.service.AlarmService;
import com.yz.service.CameraService;
import com.yz.utils.DateTimeKit;
import com.yz.utils.FileUtils;

@Controller
@RequestMapping("/facecloud")
public class FacecloudController {

	@Autowired
	private HttpRequestService requestService;

	@Autowired
	private CameraService cameraService;

	@Autowired
	private FaceCameraService faceCameraService;

	@Autowired
	private FaceImageService faceImageService;

	@Autowired
	private AlarmService alarmService;

	private static final Comparator<SearchMessage> COMPARATOR = new Comparator<SearchMessage>() {
		public int compare(SearchMessage o1, SearchMessage o2) {
			return o1.compareTo(o2);// 运用SearchMessage类的compareTo方法比较两个对象
		}
	};

	/*
	 * 经测试 reboot接口无效
	 * 
	 * @RequestMapping("/reboot") public @ResponseBody BasicResultMessage
	 * reboot() throws Exception {
	 * 
	 * return requestService.reboot(); }
	 */

	@RequestMapping("/addcameratocloud")
	public @ResponseBody AjaxMessage addCameraToCloud(Integer id) throws Exception {

		AjaxMessage ajaxMessage = new AjaxMessage();

		int cameraid = 0;
		Camera camera = cameraService.findCameraById(id);

		if (camera == null) {
			ajaxMessage.setMessage("没有当前设备");
			return ajaxMessage;
		}

		CameraResultMessage cameraResultMessage = new CameraResultMessage();

		// 通过服务器数据库中cameraid判断当前设备是否已经添加到人脸服务器
		if (camera.getCameraid() == null || camera.getCameraid() == 0) {
			CameraMessage cameraMessage = new CameraMessage();

			cameraMessage.setCamera_name(camera.getCnumber() + "_" + DateTimeKit.getLocalTime());
			cameraMessage.setCamera_mode(0);
			if (faceCameraService.setRtspURL(camera.getSipid()) == null) {
				ajaxMessage.setMessage("摄像头rtsp流配置错误,请确保系统rtsp服务地址配置正确");
				return ajaxMessage;
			}
			cameraMessage.setUrl(faceCameraService.setRtspURL(camera.getSipid()));
			cameraMessage.setDb_id_list("1"); // 人脸库
			cameraMessage.setNode_id(0);
			cameraMessage.setFixed_host(0);
			cameraMessage.setMt_policy_id(1); // 策略

			cameraResultMessage = requestService.addCamera(cameraMessage);

			// 判断登录状态
			if (!faceCameraService.checkLoginState(cameraResultMessage.getRet())) {
				// 如果未登录，则进行登录
				int result = faceCameraService.operationLogin(0);
				if (result == 0) {
					cameraResultMessage = requestService.addCamera(cameraMessage);
				} else if (result == 4011) {
					ajaxMessage.setMessage("人脸服务器登录超时");
					return ajaxMessage;
				} else if (result == 41) {
					ajaxMessage.setMessage("人脸服务器权限过期");
					return ajaxMessage;
				}
			}
			if (cameraResultMessage.getRet() == 41) {
				ajaxMessage.setMessage("人脸服务器权限过期");
				return ajaxMessage;
			}
			if (cameraResultMessage.getRet() == 4028) {

				String message = "人脸服务器挂载的摄像头已超出上限.";

				// 第一步优化：删除人脸服务器中无效的摄像头(无效：cameraid在gzdl的camera表中不存在)
				int optimizeCameraSize = faceCameraService.optimizeCameraOnFaceServer();

				if (optimizeCameraSize > 0) {
					message = message + "系统已自动为您优化" + optimizeCameraSize + "台设备,请稍后点击开始布控.";
				} else {
					// 第二步优化：删除人脸服务器中未布控的摄像头
					faceCameraService.deleteCameraOnFaceServer();
					message = message + "系统优化无可优化设备请稍后再试";
				}

				ajaxMessage.setMessage(message);
				return ajaxMessage;
			}

			if (cameraResultMessage.getCamera_list() != null && cameraResultMessage.getCamera_list().size() > 0) {
				cameraid = cameraResultMessage.getCamera_list().get(0).getCamera_id();
			}
		} else {
			cameraid = camera.getCameraid();
		}

		if (cameraid == 0) {
			ajaxMessage.setMessage("布控失败:无法获取人脸服务器中摄像头对应的cameraid.");
			return ajaxMessage;
		}

		// 判断人脸服务器中是否存在当前cameraid的摄像头
		if (faceCameraService.checkCameraOnFaceServer(cameraid) == null) {
			faceCameraService.updateCamera(id, 0, false);// 布控成功修改数据库
			ajaxMessage.setMessage("布控失败,人脸服务器中暂无当前设备.");
			return ajaxMessage;
		}

		// 判断人脸服务器中当前cameraid的摄像头布控状态
		if (!faceCameraService.checkCameraStateOnFaceServer(cameraid)) {
			cameraResultMessage = requestService.recognition(cameraid);// 布控下发
		}

		if (cameraResultMessage.getRet() == 0) {
			faceCameraService.updateCamera(id, cameraid, true);// 布控成功修改数据库
			ajaxMessage.setMessage("布控成功");
			ajaxMessage.setErrorCode(1);
			return ajaxMessage;
		} else {
			faceCameraService.updateCamera(id, cameraid, false);// 布控成功修改数据库
			ajaxMessage.setMessage("布控失败：" + cameraResultMessage.getRet_mes());
			return ajaxMessage;
		}

	}

	@RequestMapping("/getalarmvos")
	public @ResponseBody AjaxMessage getAlarmVOs(Integer id) throws Exception {

		AjaxMessage ajaxMessage = new AjaxMessage();

		Camera camera = cameraService.findCameraById(id);

		if (camera != null && camera.getCameraid() != null && camera.getCameraid() != 0) {

			Integer cameraid = camera.getCameraid();

			if (faceCameraService.checkCameraOnFaceServer(cameraid) == null) {
				faceCameraService.updateCamera(id, 0, false);// 查询到当前摄像头未添加到人脸服务器,修改数据库
				ajaxMessage.setErrorCode(0);
				ajaxMessage.setMessage("获取告警记录失败，当前摄像头已被删除，请重新布控");
				return ajaxMessage;
			} else {
				if (!faceCameraService.checkCameraStateOnFaceServer(cameraid)) {
					ajaxMessage.setErrorCode(2);
					ajaxMessage.setMessage("获取告警记录失败，当前摄像头未开启布控状态");
					faceCameraService.updateCamera(id, cameraid, false);// 查询到未布控,修改数据库
					return ajaxMessage;
				}
			}

			AlarmRequestMessage alarmRequestMessage = new AlarmRequestMessage();
			alarmRequestMessage.setCamera_id_list(camera.getCameraid() + "");
			alarmRequestMessage.setAlarm_type(9);

			AlarmResultMessage alarmResultMessage = requestService.getAlarms(alarmRequestMessage);

			if (!faceCameraService.checkLoginState(alarmResultMessage.getRet())) {
				int result = faceCameraService.operationLogin(0);
				if (result == 0) {
					alarmResultMessage = requestService.getAlarms(alarmRequestMessage);
					ajaxMessage.setErrorCode(1);
				} else if (result == 4011) {
					ajaxMessage.setErrorCode(3);
					ajaxMessage.setMessage("获取告警记录失败，人脸服务器连接超时");
					return ajaxMessage;
				}
			}

			List<AlarmMessageVO> alarmMessageVOs = new ArrayList<AlarmMessageVO>();
			if (alarmResultMessage != null && alarmResultMessage.getAlarmMessages() != null) {
				for (int i = 0; i < alarmResultMessage.getAlarmMessages().size(); i++) {
					AlarmMessage alarm = alarmResultMessage.getAlarmMessages().get(i);

					AlarmMessageVO vo = new AlarmMessageVO();
					vo.setAlarm_id(alarm.getAlarm_id());
					vo.setAlarm_time(alarm.getAlarm_time());
					vo.setCamera_name(alarm.getCamera_name());
					vo.setPhoto_host_id(alarm.getPhoto_host_id());
					vo.setPhoto_name(alarm.getFace_name());
					//// 0,抓拍无告警 1,黑名单告警2,白名单告警
					switch (alarm.getAlarm_type()) {
					case 0:
						vo.setAlarm_typename("非工作人员");
						break;
					case 1:
						vo.setAlarm_typename("工作人员");

						List<SearchMessage> searchMessages = alarm.getSearchMessages();

						// 比较获取阈值最高的人员
						if (searchMessages != null && searchMessages.size() > 0) {
							Collections.sort(searchMessages, COMPARATOR);
							SearchMessage searchMessage = searchMessages.get(searchMessages.size() - 1);

							if (searchMessage.getPerson_name() != null && !searchMessage.getPerson_name().equals("")) {
								vo.setPerson_name(searchMessage.getPerson_name());
							} else {
								vo.setPerson_name("");
							}
							vo.setPerson_id(searchMessage.getPerson_id());
						}
						break;
					case 2:
						vo.setAlarm_typename("非工作人员");
						break;
					default:
						break;
					}
					alarmMessageVOs.add(vo);

					Alarm alarmModle = new Alarm();
					alarmModle.setCameraid(camera.getId());
					alarmModle.setAlarmtime(vo.getAlarm_time());
					alarmModle.setPername(vo.getPerson_name());
					alarmModle.setPertype(alarm.getAlarm_type());
					alarmService.saveAlarm(alarmModle);

				}
				ajaxMessage.setAlarmMessages(alarmMessageVOs);
			}

			return ajaxMessage;
		} else {

			ajaxMessage.setErrorCode(0);

			ajaxMessage.setMessage("获取告警记录失败，当前摄像头未添加到人脸服务器");
			return ajaxMessage;
		}

	}

	@RequestMapping("/face")
	public String face(String person_id, Model model) throws Exception {

		FaceRequestMessage faceRequestMessage = new FaceRequestMessage();

		if (person_id != null && !person_id.equals("")) {
			faceRequestMessage.setDb_id("1");
			faceRequestMessage.setPerson_id(person_id);

			FaceResultMessage faceResultMessage = requestService.getFaces(faceRequestMessage);

			if (faceResultMessage.getRet() == 0 && faceResultMessage.getFace_list() != null
					&& faceResultMessage.getFace_list().size() > 0) {
				FaceMessage faceMessage = faceResultMessage.getFace_list().get(0);

				faceRequestMessage.setFace_id(faceMessage.getFace_id());

				FaceDataResultMessage faceDataResultMessage = requestService.getFace(faceRequestMessage);

				if (faceDataResultMessage.getRet() == 0) {
					model.addAttribute("content", faceDataResultMessage.getFace_data().getImage_data().getContent());
					return "image";
				}
			}
		}
		model.addAttribute("message", "未查询到当前人员照片.");
		return "error";

	}

	@RequestMapping("/image")
	public String image(String photo_name, Integer host_id, Model model) throws Exception {

		ImageRequestMessage imageRequestMessage = new ImageRequestMessage();

		if (photo_name != null && !photo_name.equals("") && host_id != null && host_id != 0) {
			imageRequestMessage.setFilename(photo_name);
			;
			imageRequestMessage.setPhoto_host_id(host_id);

			ImageResultMessage imageResultMessage = requestService.getImage(imageRequestMessage);

			if (imageResultMessage.getRet() == 0 && imageResultMessage.getImage() != null) {

				ImageMessage imageMessage = imageResultMessage.getImage();
				model.addAttribute("content", imageMessage.getContent());
				return "image";
			}
		}
		model.addAttribute("message", "未查询到当前抓拍照片.");
		return "error";

	}

	@RequestMapping("/faceimage")
	public String faceimage(String photo_name, Integer host_id, Model model) throws Exception {

		ImageRequestMessage imageRequestMessage = new ImageRequestMessage();

		if (photo_name != null && !photo_name.equals("") && host_id != null && host_id != 0) {
			imageRequestMessage.setFilename(photo_name);
			;
			imageRequestMessage.setPhoto_host_id(host_id);

			ImageResultMessage imageResultMessage = requestService.getFaceImage(imageRequestMessage);

			if (imageResultMessage.getRet() == 0 && imageResultMessage.getImage() != null) {

				ImageMessage imageMessage = imageResultMessage.getImage();
				model.addAttribute("content", imageMessage.getContent());
				return "image";
			}
		}
		model.addAttribute("message", "未查询到当前抓拍人脸.");
		return "error";

	}

	@RequestMapping("/login")
	public @ResponseBody LoginResultMessage login() throws Exception {

		return faceCameraService.login();
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
		cameraService.updateCamera(id, camera);
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

	// 专门给科腾技术有限公司用的接口
	@RequestMapping("/getAlarmVOsss")
	public @ResponseBody AjaxMessage getAlarmVOsss(Integer id) throws Exception {

		AjaxMessage ajaxMessage = new AjaxMessage();

		Camera camera = cameraService.findCameraById(id);

		if (camera != null && camera.getCameraid() != null && camera.getCameraid() != 0) {

			Integer cameraid = camera.getCameraid();

			if (faceCameraService.checkCameraOnFaceServer(cameraid) == null) {
				faceCameraService.updateCamera(id, 0, false);// 查询到当前摄像头未添加到人脸服务器,修改数据库
				ajaxMessage.setErrorCode(0);
				ajaxMessage.setMessage("获取告警记录失败，当前摄像头已被删除，请重新布控");
				return ajaxMessage;
			} else {
				if (!faceCameraService.checkCameraStateOnFaceServer(cameraid)) {
					ajaxMessage.setErrorCode(2);
					ajaxMessage.setMessage("获取告警记录失败，当前摄像头未开启布控状态");
					faceCameraService.updateCamera(id, cameraid, false);// 查询到未布控,修改数据库
					return ajaxMessage;
				}
			}

			AlarmRequestMessage alarmRequestMessage = new AlarmRequestMessage();
			alarmRequestMessage.setCamera_id_list(camera.getCameraid() + "");
			alarmRequestMessage.setAlarm_type(9);

			AlarmResultMessage alarmResultMessage = requestService.getAlarms(alarmRequestMessage);

			if (!faceCameraService.checkLoginState(alarmResultMessage.getRet())) {
				int result = faceCameraService.operationLogin(0);
				if (result == 0) {
					alarmResultMessage = requestService.getAlarms(alarmRequestMessage);
					ajaxMessage.setErrorCode(1);
				} else if (result == 4011) {
					ajaxMessage.setErrorCode(3);
					ajaxMessage.setMessage("获取告警记录失败，人脸服务器连接超时");
					return ajaxMessage;
				}
			}

			List<AlarmMessageVO> alarmMessageVOs = new ArrayList<AlarmMessageVO>();
			if (alarmResultMessage != null && alarmResultMessage.getAlarmMessages() != null) {
				for (int i = 0; i < alarmResultMessage.getAlarmMessages().size(); i++) {
					AlarmMessage alarm = alarmResultMessage.getAlarmMessages().get(i);

					AlarmMessageVO vo = new AlarmMessageVO();
					vo.setAlarm_id(alarm.getAlarm_id());
					vo.setAlarm_time(alarm.getAlarm_time());
					vo.setCamera_name(alarm.getCamera_name());
					vo.setPhoto_host_id(alarm.getPhoto_host_id());
					vo.setPhoto_name(alarm.getPhoto_name());
					//// 0,抓拍无告警 1,黑名单告警2,白名单告警
					switch (alarm.getAlarm_type()) {
					case 0:
						vo.setAlarm_typename("非工作人员");
						// 不是工作人员 ，请求抓拍图片
						ImageRequestMessage imageRequestMessage = new ImageRequestMessage();

						if (vo.getPhoto_name() != null && !vo.getPhoto_name().equals("")
								&& new Integer(vo.getPhoto_host_id()) != null && vo.getPhoto_host_id() != 0) {
							imageRequestMessage.setFilename(vo.getPhoto_name());
							imageRequestMessage.setPhoto_host_id(vo.getPhoto_host_id());

							ImageResultMessage imageResultMessage = requestService.getImage(imageRequestMessage);

							if (imageResultMessage.getRet() == 0 && imageResultMessage.getImage() != null) {

								ImageMessage imageMessage = imageResultMessage.getImage();
								vo.setPhoto_url(imageMessage.getContent());
							}
						}
						break;
					case 1:
						vo.setAlarm_typename("工作人员");

						List<SearchMessage> searchMessages = alarm.getSearchMessages();

						// 比较获取阈值最高的人员
						if (searchMessages != null && searchMessages.size() > 0) {
							Collections.sort(searchMessages, COMPARATOR);
							SearchMessage searchMessage = searchMessages.get(searchMessages.size() - 1);

							if (searchMessage.getPerson_name() != null && !searchMessage.getPerson_name().equals("")) {
								vo.setPerson_name(searchMessage.getPerson_name());
							} else {
								vo.setPerson_name("");
							}
							vo.setPerson_id(searchMessage.getPerson_id());

							// 请求图片
							FaceRequestMessage faceRequestMessage = new FaceRequestMessage();

							if (vo.getPerson_id() != null && !vo.getPerson_id().equals("")) {
								faceRequestMessage.setDb_id("1");
								faceRequestMessage.setPerson_id(vo.getPerson_id());

								FaceResultMessage faceResultMessage = requestService.getFaces(faceRequestMessage);

								if (faceResultMessage.getRet() == 0 && faceResultMessage.getFace_list() != null
										&& faceResultMessage.getFace_list().size() > 0) {
									FaceMessage faceMessage = faceResultMessage.getFace_list().get(0);

									faceRequestMessage.setFace_id(faceMessage.getFace_id());

									FaceDataResultMessage faceDataResultMessage = requestService
											.getFace(faceRequestMessage);

									if (faceDataResultMessage.getRet() == 0) {
										vo.setPhoto_url(
												faceDataResultMessage.getFace_data().getImage_data().getContent());
									}
								}
							}

						}
						break;
					case 2:
						vo.setAlarm_typename("非工作人员");
						break;
					default:
						break;
					}
					alarmMessageVOs.add(vo);

					Alarm alarmModle = new Alarm();
					alarmModle.setCameraid(camera.getId());
					alarmModle.setAlarmtime(vo.getAlarm_time());
					alarmModle.setPername(vo.getPerson_name());
					alarmModle.setPertype(alarm.getAlarm_type());
					alarmService.saveAlarm(alarmModle);

				}
				ajaxMessage.setAlarmMessages(alarmMessageVOs);
			}

			return ajaxMessage;
		} else {

			ajaxMessage.setErrorCode(0);

			ajaxMessage.setMessage("获取告警记录失败，当前摄像头未添加到人脸服务器");
			return ajaxMessage;
		}

	}

	@RequestMapping("/reconImage")
	public @ResponseBody String reconImage(@RequestParam MultipartFile[] photo_file) throws Exception {

		String base64photo = "";
		if (photo_file != null && photo_file.length > 0) {
			System.out.println("文件大小:" + photo_file[0].getSize());
			MultipartFile file = photo_file[0];
			if (file.getSize() < 5 * 1024 * 1024) {

				// 获取图片的字节流
				// byte[] bytePhoto = FileUtils.file2Byte(photo_file[0]);
				// 压缩图片
				// base64photo = FileUtils.resize(bytePhoto, 300, 0.7f);
				// System.out.println("字符长度:" + base64photo.length());

				base64photo = FileUtils.file2String(file);

				ImageMessage imageMessage = new ImageMessage();

				String type = file.getContentType();// 获取文件MIME类型

				imageMessage.setType(type);
				imageMessage.setContent(base64photo);
				return faceImageService.getPersonMatchingMessage(imageMessage);
			} else {
				return "照片大小不能超过1M!";
			}
		}
		return "未上传照片!";
		

	}

}
