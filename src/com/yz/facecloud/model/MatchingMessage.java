package com.yz.facecloud.model;

/**
 * 人脸识别返回信息-人脸识别返回列表-识别信息
 * 
 * @author Administrator
 *
 */

public class MatchingMessage {

	private String person_id;
	private String name;
	private String birth;
	private String sex;
	private Integer card_type;
	private String id_card;
	private String native_place;
	private String addr;
	private String phone;
	private Integer similarity;
	private Integer db_id;
	private String create_date;
	private String url;
	private String defaut_face_image_url;

	public String getPerson_id() {
		return person_id;
	}

	public void setPerson_id(String person_id) {
		this.person_id = person_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getCard_type() {
		return card_type;
	}

	public void setCard_type(Integer card_type) {
		this.card_type = card_type;
	}

	public String getId_card() {
		return id_card;
	}

	public void setId_card(String id_card) {
		this.id_card = id_card;
	}

	public String getNative_place() {
		return native_place;
	}

	public void setNative_place(String native_place) {
		this.native_place = native_place;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getSimilarity() {
		return similarity;
	}

	public void setSimilarity(Integer similarity) {
		this.similarity = similarity;
	}

	public Integer getDb_id() {
		return db_id;
	}

	public void setDb_id(Integer db_id) {
		this.db_id = db_id;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDefaut_face_image_url() {
		return defaut_face_image_url;
	}

	public void setDefaut_face_image_url(String defaut_face_image_url) {
		this.defaut_face_image_url = defaut_face_image_url;
	}

}
