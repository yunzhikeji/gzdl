package com.yz.facecloud.model;

public class SearchMessage implements Comparable<SearchMessage> {

	private String search_result_id;
	private String db_id;
	private String person_name;
	private String person_id;
	private String face_url;
	private String birth;
	private String sex;
	private Double similarity;

	public String getSearch_result_id() {
		return search_result_id;
	}

	public void setSearch_result_id(String search_result_id) {
		this.search_result_id = search_result_id;
	}

	public String getDb_id() {
		return db_id;
	}

	public void setDb_id(String db_id) {
		this.db_id = db_id;
	}

	public String getPerson_name() {
		return person_name;
	}

	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}

	public String getPerson_id() {
		return person_id;
	}

	public void setPerson_id(String person_id) {
		this.person_id = person_id;
	}

	public String getFace_url() {
		return face_url;
	}

	public void setFace_url(String face_url) {
		this.face_url = face_url;
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

	public Double getSimilarity() {
		return similarity;
	}

	public void setSimilarity(Double similarity) {
		this.similarity = similarity;
	}

	@Override
	public int compareTo(SearchMessage o) {
		// TODO Auto-generated method stub
		if (this == o) {
			return 0;
		} else if (o != null && o instanceof SearchMessage) {
			SearchMessage searchMessage = (SearchMessage) o;
			if (this.getSimilarity() <= searchMessage.getSimilarity()) {
				return -1;
			} else {
				return 1;
			}
		} else {
			return -1;
		}
	}

}
