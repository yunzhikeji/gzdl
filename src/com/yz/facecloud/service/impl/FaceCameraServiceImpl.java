package com.yz.facecloud.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.yz.facecloud.model.CameraMessage;
import com.yz.facecloud.model.CameraRequestMessage;
import com.yz.facecloud.model.CameraResultMessage;
import com.yz.facecloud.model.FaceUser;
import com.yz.facecloud.model.LoginRequestMessage;
import com.yz.facecloud.model.LoginResultMessage;
import com.yz.facecloud.service.FaceCameraService;
import com.yz.facecloud.service.HttpRequestService;
import com.yz.facecloud.util.MD5Util;
import com.yz.po.Camera;
import com.yz.po.CameraCustom;
import com.yz.service.CameraService;

public class FaceCameraServiceImpl implements FaceCameraService {

	@Autowired
	private CameraService cameraService;

	@Autowired
	private HttpRequestService requestService;

	private String rstp_url;

	@Resource(name = "faceUser")
	private FaceUser faceUser;

	public void updateCamera(Integer id, int cameraid, boolean success) throws Exception {
		// TODO Auto-generated method stub
		CameraCustom cameraCustom = new CameraCustom();
		cameraCustom.setCameraid(cameraid); // 布控成功将cameraid保存到数据库
		if (success) {
			cameraCustom.setStat(1); // 布控成功就将stat设为1，否则为0
		} else {
			cameraCustom.setStat(0); // 布控成功就将stat设为1，否则为0
		}
		cameraService.updateCamera(id, cameraCustom);
	}

	public String setRtspURL(String sipid) {
		if (sipid != null) {

			if (rstp_url == null) {
				return null;
			}
			String url = rstp_url + sipid + "/1";
			return url;
		}
		return null;
	}

	public boolean checkLoginState(int ret) {
		if (ret == 0) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean checkCameraStateOnFaceServer(int cameraid) {

		CameraMessage cameraMessage = this.checkCameraOnFaceServer(cameraid);

		if (checkCameraOnFaceServer(cameraid) != null) {
			if (cameraMessage.getCamera_state() == 2)// 是否为布控状态
			{
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	@Override
	public CameraMessage checkCameraOnFaceServer(int cameraid) {
		CameraRequestMessage requestMessage = new CameraRequestMessage();
		requestMessage.setCamera_id(cameraid);
		CameraResultMessage cameraResultMessage = requestService.getCameras(requestMessage);

		if (!this.checkLoginState(cameraResultMessage.getRet())) {
			if (this.setLoginState() != 1) {
				return null;
			}
		}
		if (cameraResultMessage.getCamera_list() != null && cameraResultMessage.getCamera_list().size() > 0) {
			return cameraResultMessage.getCamera_list().get(0);
		} else {
			return null;
		}
	}

	public LoginResultMessage login() throws Exception {

		LoginRequestMessage requestMessage = new LoginRequestMessage();
		if (faceUser != null) {
			requestMessage.setUser_name(faceUser.getUsername());
			requestMessage.setUser_pwd(MD5Util.getMD5(faceUser.getPassword()));
			requestMessage.setMode(faceUser.getMode());
		}

		return requestService.login(requestMessage);
	}

	public int setLoginState() {

		int result = 0;
		int counter = 0;

		try {
			while (true) {
				result = this.login().getRet();
				if (result == 0)// 登录成功 请求超时
				{
					result = 1;
					break;
				}
				if (result == 4011) {
					result = 4011;
					break;
				}

				counter++;
				if (counter == 8) {
					result = 4011;
					break;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = 4011;
		}
		return result;

	}

	@Override
	public int optimizeCameraOnFaceServer() throws Exception {
		// TODO Auto-generated method stub
		CameraRequestMessage requestMessage = new CameraRequestMessage();
		CameraResultMessage cameraResultMessage = requestService.getCameras(requestMessage);

		List<CameraMessage> cameraMessagesDelete = new ArrayList<CameraMessage>();// 需被删除列表

		if (cameraResultMessage.getCamera_list() != null && cameraResultMessage.getCamera_list().size() > 3) {

			List<CameraMessage> cameraMessages = cameraResultMessage.getCamera_list();

			for (int i = 0; i < cameraMessages.size(); i++) {

				CameraMessage cameraMessage = cameraMessages.get(i);

				int camera_id = cameraMessage.getCamera_id();

				Camera camera = cameraService.findCameraByCameraid(camera_id);
				// 当本地摄像头中没有查询到人脸服务器中的设备时，则删除人脸服务器中的摄像头
				if (camera == null) {

					// 如果当前摄像机为布控状态，则停止布控
					if (cameraMessage.getCamera_state() == 2) {
						requestService.recognitionStop(camera_id);
					}
					requestService.deleteCamera(camera_id);

					cameraMessagesDelete.add(cameraMessage);
				}
			}
		}
		return cameraMessagesDelete.size();
	}

	@Override
	public void deleteCameraOnFaceServer() {

		CameraRequestMessage requestMessage = new CameraRequestMessage();
		CameraResultMessage cameraResultMessage = requestService.getCameras(requestMessage);

		if (cameraResultMessage.getCamera_list() != null && cameraResultMessage.getCamera_list().size() > 3) {

			List<CameraMessage> cameraMessages = cameraResultMessage.getCamera_list();

			for (int i = 0; i < cameraMessages.size(); i++) {

				CameraMessage cameraMessage = cameraMessages.get(i);

				int camera_state = cameraMessage.getCamera_state();

				// 未布控的摄像头
				if (camera_state != 2) {
					requestService.deleteCamera(cameraMessage.getCamera_id());
				}
			}
		}

	}

	public String getRstp_url() {
		return rstp_url;
	}

	public void setRstp_url(String rstp_url) {
		this.rstp_url = rstp_url;
	}

	public FaceUser getFaceUser() {
		return faceUser;
	}

	public void setFaceUser(FaceUser faceUser) {
		this.faceUser = faceUser;
	}

}
