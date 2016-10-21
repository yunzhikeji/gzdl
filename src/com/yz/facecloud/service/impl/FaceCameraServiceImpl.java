package com.yz.facecloud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.yz.facecloud.model.CameraMessage;
import com.yz.facecloud.model.CameraRequestMessage;
import com.yz.facecloud.model.CameraResultMessage;
import com.yz.facecloud.service.FaceCameraService;
import com.yz.facecloud.service.HttpRequestService;
import com.yz.po.CameraCustom;
import com.yz.service.CameraService;

public class FaceCameraServiceImpl implements FaceCameraService {

	@Autowired
	private CameraService cameraService;

	@Autowired
	private HttpRequestService requestService;

	private String rstp_url;

	public void updateCamera(Integer id, int cameraid, boolean success) throws Exception {
		// TODO Auto-generated method stub
		CameraCustom cameraCustom = new CameraCustom();
		if (success) {
			cameraCustom.setCameraid(cameraid); // 布控成功将cameraid保存到数据库
			cameraCustom.setStat(1); // 布控成功就将stat设为1，否则为0
		} else {
			cameraCustom.setCameraid(cameraid); // 布控成功将cameraid保存到数据库
			cameraCustom.setStat(0); // 布控成功就将stat设为1，否则为0
		}

		cameraService.updateCamera(id, cameraCustom);
	}

	public String setRtspURL(String sipid) {
		if (sipid != null) {

			if (rstp_url == null) {
				return null;
			}
			String url =rstp_url + sipid + "/1";
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
			System.out.println("布控状态:" + cameraMessage.getCamera_state());
			if (cameraMessage.getCamera_state() == 1)// 是否为布控状态
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

		if (cameraResultMessage.getCamera_list() != null && cameraResultMessage.getCamera_list().size() > 0) {
			return cameraResultMessage.getCamera_list().get(0);
		} else {
			return null;
		}
	}

	public String getRstp_url() {
		return rstp_url;
	}

	public void setRstp_url(String rstp_url) {
		this.rstp_url = rstp_url;
	}

}
