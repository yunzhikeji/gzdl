package com.yz.facecloud.model;

/**
 * 人脸识别返回信息-人脸识别返回列表-人脸区域
 * 
 * @author Administrator
 *
 */
public class PersonRectMessage {

	private Integer left;
	private Integer top;
	private Integer right;
	private Integer bottom;
	private Double blur;

	public Integer getLeft() {
		return left;
	}

	public void setLeft(Integer left) {
		this.left = left;
	}

	public Integer getTop() {
		return top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}

	public Integer getRight() {
		return right;
	}

	public void setRight(Integer right) {
		this.right = right;
	}

	public Integer getBottom() {
		return bottom;
	}

	public void setBottom(Integer bottom) {
		this.bottom = bottom;
	}

	public Double getBlur() {
		return blur;
	}

	public void setBlur(Double blur) {
		this.blur = blur;
	}

}
