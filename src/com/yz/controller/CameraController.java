package com.yz.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yz.po.Camera;
import com.yz.po.Organize;
import com.yz.service.CameraService;
import com.yz.service.OrganizeService;
import com.yz.vo.CameraQueryVO;
import com.yz.vo.CameraVO;
import com.yz.vo.OrganizeQueryVO;

@Controller
@RequestMapping("/camera")
public class CameraController {

	@Autowired
	private CameraService cameraService;

	@Autowired
	private OrganizeService organizeService;

	// 单个监控画面
	@RequestMapping(value = "singleVideo", method = { RequestMethod.GET })
	public String singleVideo(@RequestParam(value = "id", required = false) Integer id, ModelMap map) throws Exception {
		Camera camera = cameraService.findCameraById(id);
		if(camera==null)
		{
			String message  = "系统没有该设备.";
			map.put("message", message);
			return "error";
		}
		Organize organize = organizeService.selectByPrimaryKey(camera.getOrganizeid());
		String cnumber  = camera.getCnumber();
		if(cnumber==null)
		{
			String message  = "当前设备编号为空";
			map.put("message", message);
			return "error";
		}
		map.put("camera", camera);
		map.put("organize", organize);
		if(cnumber.equals("0010")){
			return "exhibitionVideo";
		}
		return "singleVideo";
	}

	// 地图获取设备列表
	/**
	 * @param number
	 * @return
	 * @throws Exception
	 */
	// 地图返回
	@RequestMapping("/getCameras")
	public @ResponseBody List<Camera> getAllCameras(OrganizeQueryVO organizeQueryVO, CameraQueryVO cameraQueryVO,
			HttpSession session) throws Exception {

		List<Camera> cameras = new ArrayList<Camera>();

		Integer userOrganizeid = (Integer) session.getAttribute("organizeid");

		// 超级管理员
		if (userOrganizeid == 0) {
			cameras = cameraService.findCameraListByOrganizeQueryVO(organizeQueryVO);
			if(cameraQueryVO.getNumber()!=null&&!cameraQueryVO.getNumber().trim().equals(""))
			{
				List<Camera> camerasByNumber = cameraService.findCameraListByNumber(cameraQueryVO.getNumber());
				cameras.retainAll(camerasByNumber);//取交集
			}
			return cameras;
		}

		// 用户
		cameraQueryVO.setUserOrganizeid(userOrganizeid);

		List<Camera> camerasQuery = cameraService.findCameraListByOrganizeQueryVO(organizeQueryVO);// 根据前台查询条件获得设备列表

		cameras = cameraService.getCamerasByNumberAndOrganizeid(cameraQueryVO);// 根据用户自身权限获得设备列表

		cameras.retainAll(camerasQuery);// 取交集

		return cameras;
	}

	// 设备管理列表
	@RequestMapping("/cameraList")
	public ModelAndView cameraList(OrganizeQueryVO organizeQueryVO, String number, Integer hire) throws Exception {
		List<Camera> cameraList = null;
		if (hire == null) { // hire为空表示前台没传参数，显示所有
			cameraList = cameraService.findCameraListByOrganizeQueryVO(organizeQueryVO);// 根据查询条件获得设备

			if (number != null && !number.trim().equals("")) {
				List<Camera> cameraListNumber = cameraService.findCameraListByNumber(number);// 根据编号模糊查询的设备
				cameraList.retainAll(cameraListNumber);// 取交集
			}
			
		} else if (hire == 1) { // hire等于1表示显示已经出租的设备
			cameraList = cameraService.findHiredCameralist();
		} else if (hire == 2) {
			cameraList = cameraService.findUnhiredCameralist();
		}

		// 返回设备信息同时要返回设备所属机构的信息，创建一个CameraVo对象,根据organizeId查询organize
		List<CameraVO> cameraVoList = new ArrayList<>();
		for (int i = 0; cameraList != null && i < cameraList.size(); i++) {
			Camera camera = cameraList.get(i);
			CameraVO cameraVo = new CameraVO();
			cameraVo.setId(camera.getId());
			cameraVo.setSipid(camera.getSipid());
			cameraVo.setSipserverid(camera.getSipserverid());
			cameraVo.setLng(camera.getLng());
			cameraVo.setLat(camera.getLat());
			cameraVo.setCnumber(camera.getCnumber());
			cameraVo.setCname(camera.getCname());
			cameraVo.setVoltage(camera.getVoltage());
			cameraVo.setTemperature(camera.getTemperature());
			cameraVo.setStatus(camera.getStatus());
			cameraVo.setIscontroll(camera.getIscontroll());
			cameraVo.setStat(camera.getStat());
			cameraVo.setState(camera.getState());
			cameraVo.setCameraid(camera.getCameraid());
			cameraVo.setOrganizeid(camera.getOrganizeid());
			if (camera.getOrganizeid() != null) {
				if (camera.getOrganizeid() == 0) {
					cameraVo.setOrganizeName("亿弘淼能源科技有限公司");
				} else
					cameraVo.setOrganizeName(organizeService.selectByPrimaryKey(camera.getOrganizeid()).getName());
			}
			cameraVoList.add(cameraVo);
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("cameraVoList", cameraVoList);
		modelAndView.setViewName("camera/cameraList");
		return modelAndView;
	}

	// 去设备入库页面
	@RequestMapping("/toAdd")
	public String toAdd() throws Exception {
		return "camera/cameraAdd";
	}

	// 去设备编辑和出租页面，回显设备信息
	@RequestMapping("/toUpdate")
	public String toUpdate(HttpServletRequest request, Model model, Integer id) throws Exception {
		Camera camera = cameraService.findCameraById(id);
		model.addAttribute("camera", camera);
		return "camera/cameraUpdate";
	}

	// 去设备编辑和出租页面，回显设备信息
	@RequestMapping("/toLease")
	public String toLease(HttpServletRequest request, Model model, Integer id) throws Exception {
		Camera camera = cameraService.findCameraById(id);
		List<Organize> organizeList = organizeService.findOrganizeList();
		model.addAttribute("camera", camera);
		model.addAttribute("organizeList", organizeList);
		return "camera/cameraLease";
	}

	// 请求添加一个设备
	@RequestMapping("/addCamera")
	public String addCamera(Camera camera) throws Exception {
		camera.setOrganizeid(0); // 设备入库，将organizeid初始为0，表示入了亿弘淼的库存
		cameraService.insertCamera(camera);
		return "success";
	}

	// 请求删除一个设备
	@RequestMapping("/deleteCamera")
	public void deleteCamera(Integer id) throws Exception {
		cameraService.deleCamera(id);
	}

	@RequestMapping("/updateCameraSubmit")
	public String updateCameraSubmit(Model model, HttpServletRequest request, Integer id, Camera camera)
			throws Exception {
		cameraService.updateCamera(id, camera);
		return "success";
	}

	// 根据id回收设备，将organizeid初始为0
	@RequestMapping("/recycle")
	public String recycle(Integer id) throws Exception {
		Camera camera = cameraService.findCameraById(id);
		camera.setOrganizeid(0);
		cameraService.updateCamera(id, camera);
		return "redirect:/camera/cameraList";
	}

	// 设置设备租凭信息
	@RequestMapping("/updateCameraLease")
	public String updateCameraLease(Model model, HttpServletRequest request, Integer id, String organizeName)
			throws Exception {

		System.out.println(id);
		System.out.println(organizeName);

		Camera camera = cameraService.findCameraById(id);

		if (organizeName.equals("回收(未出租)")) {
			camera.setOrganizeid(0);
		} else {
			Organize organize = organizeService.findOrganizeByName(organizeName);
			camera.setOrganizeid(organize.getId());
		}

		cameraService.updateCamera(id, camera);

		return "success";
	}

}
