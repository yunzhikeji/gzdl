package com.yz.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yz.mapper.FaceMapper;
import com.yz.po.Face;
import com.yz.service.FaceService;

public class FaceServiceImpl implements FaceService {
	
	@Autowired
	private FaceMapper faceMapper;


	@Override
	public Face findFaceById(Integer id) throws Exception {
		
		return faceMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateFace(Integer id, Face face) throws Exception {
		face.setId(id);
		faceMapper.updateByPrimaryKey(face);
		
	}

	@Override
	public void saveFace(Face face) throws Exception {
		faceMapper.insert(face);
	}

	@Override
	public void deleteFaceById(Integer id) throws Exception {
		faceMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Face> findFaceList() throws Exception {
		
		return faceMapper.findFaceList();
	}




}
