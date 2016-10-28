package com.yz.facecloud.model;

public class FaceDataMessage {

	private String face_id;

	private ImageDataMessage image_data;

	private FeatureDataMessage feature_data;

	private Double blur;

	public String getFace_id() {
		return face_id;
	}

	public void setFace_id(String face_id) {
		this.face_id = face_id;
	}

	public ImageDataMessage getImage_data() {
		return image_data;
	}

	public void setImage_data(ImageDataMessage image_data) {
		this.image_data = image_data;
	}

	public FeatureDataMessage getFeature_data() {
		return feature_data;
	}

	public void setFeature_data(FeatureDataMessage feature_data) {
		this.feature_data = feature_data;
	}

	public Double getBlur() {
		return blur;
	}

	public void setBlur(Double blur) {
		this.blur = blur;
	}

}
