package com.yz.facecloud.model;

public class ImageRequestMessage extends BasicRequestMessage {

	private String filename;//对应photo_name
	private int photo_host_id;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getPhoto_host_id() {
		return photo_host_id;
	}

	public void setPhoto_host_id(int photo_host_id) {
		this.photo_host_id = photo_host_id;
	}

}
