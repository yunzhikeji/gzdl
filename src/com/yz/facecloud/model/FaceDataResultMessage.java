package com.yz.facecloud.model;

public class FaceDataResultMessage extends BasicResultMessage {

	private FaceDataMessage face_data;

	public FaceDataMessage getFace_data() {
		return face_data;
	}

	public void setFace_data(FaceDataMessage face_data) {
		this.face_data = face_data;
	}

}
