package com.yz.po;

public class Alarm {
    private Integer id;

    private Integer cameraid;

    private String alarmtime;

    private Integer pertype;

    private String pername;

    private String photo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCameraid() {
        return cameraid;
    }

    public void setCameraid(Integer cameraid) {
        this.cameraid = cameraid;
    }

    public String getAlarmtime() {
        return alarmtime;
    }

    public void setAlarmtime(String alarmtime) {
        this.alarmtime = alarmtime == null ? null : alarmtime.trim();
    }

    public Integer getPertype() {
        return pertype;
    }

    public void setPertype(Integer pertype) {
        this.pertype = pertype;
    }

    public String getPername() {
        return pername;
    }

    public void setPername(String pername) {
        this.pername = pername == null ? null : pername.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }
}