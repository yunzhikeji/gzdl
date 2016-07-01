package com.yz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

	@RequestMapping(value = "realTime", method = { RequestMethod.GET })
	public String realTime(@RequestParam(value = "markid", required = false) String markid, ModelMap map) {

		Site site = siteService.querySiteByMarkid(markid);

		List<Camera> cameras = cameraService.getCameraByUsefulLatlng();

		SiteCustom siteCustom = new SiteCustom();

		if (site != null) {
			siteCustom.setViewstyle(site.getViewstyle());
			siteCustom.setName(site.getName());
			siteCustom.setAreaname(site.getAreaname());
			if (cameras != null) {
				map.put("cameras", cameras);
			}
			map.put("siteCustom", siteCustom);

		} else {
			return "fail";
		}

		if (siteCustom.getViewstyle() == null || siteCustom.getViewstyle() == 1) {

			if (cameras != null && cameras.size() > 0) {
				Camera camera = cameras.get(0);

				map.put("camera", camera);
			}
			return "singleVideo";
		} else if (siteCustom.getViewstyle() == 4) {
			return "fourVideos";
		} else if (siteCustom.getViewstyle() == 9) {
			return "nineVideos";
		} else {
			return "singleVideo";
		}

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
		} else {
			return "nineVideos";
		}
	}

}
