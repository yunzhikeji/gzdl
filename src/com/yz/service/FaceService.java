package com.yz.service;

import java.util.List;

import com.yz.po.Face;

public interface FaceService {
	


	public Face findFaceById(Integer id) throws Exception;
	

	public void updateFace(Integer id,Face face) throws Exception;
	
	public void saveFace(Face face) throws Exception;
	
	public void deleteFaceById(Integer id) throws Exception;
}
