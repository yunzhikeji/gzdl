package com.yz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yz.po.Camera;
import com.yz.po.Site;
import com.yz.po.SiteCustom;
import com.yz.service.CameraService;
import com.yz.service.SiteService;

@Controller
@RequestMapping("/camera")
public class CameraController {

	@Autowired
	private CameraService cameraService;
	@Autowired
	private SiteService siteService;

	@RequestMapping(value = "singleVideo", method = { RequestMethod.GET })
	public String singleVideo(@RequestParam(value = "id", required = false) Integer id,ModelMap map)  throws Exception {
			System.out.println(id);
			Camera camera = cameraService.findCameraById(id);
			map.put("camera", camera);

			return "singleVideo";


	}
	
	@RequestMapping(value = "siteVideos")
	public String siteVideos(@RequestParam(value = "sid", required = true) int sid, ModelMap map) throws Exception {

		Site site = siteService.findSiteById(sid);

		List<Camera> cameras = cameraService.getCameraByUsefulLatlng();

		if (site != null) {

		} else {
			return "fail";
		}

		if (site.getViewstyle() == null || site.getViewstyle() == 1) {

			if (cameras != null && cameras.size() > 0) {
				Camera camera = cameras.get(0);

				map.put("camera", camera);
			}
			return "singleVideo";
		} else if (site.getViewstyle() == 4) {
			return "fourVideos";
		}else if (site.getViewstyle() == 9) {
			return "nineVideos";
		} else {
			return "singleVideo";
		}
	}
	
@RequestMapping("/getcameras")	
 public @ResponseBody List<Camera> getAllCameras() throws Exception {
	 return cameraService.findCameraList();
 }

}
