package com.yz.po;

public class Camera {
    private Integer id;

    private String sipid;

    private String sipserverid;

    private String lng;

    private String lat;

    private String cnumber;

    private String cname;

    private String markerid;

    private String voltage;

    private String temperature;

    private Integer status;// 工作状态  A=正常工作(1)，D=关机(0)，R=重启中(2)，N=未知状态(-1)

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSipid() {
        return sipid;
    }

    public void setSipid(String sipid) {
        this.sipid = sipid == null ? null : sipid.trim();
    }

    public String getSipserverid() {
        return sipserverid;
    }

    public void setSipserverid(String sipserverid) {
        this.sipserverid = sipserverid == null ? null : sipserverid.trim();
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng == null ? null : lng.trim();
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat == null ? null : lat.trim();
    }

    public String getCnumber() {
        return cnumber;
    }

    public void setCnumber(String cnumber) {
        this.cnumber = cnumber == null ? null : cnumber.trim();
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public String getMarkerid() {
        return markerid;
    }

    public void setMarkerid(String markerid) {
        this.markerid = markerid == null ? null : markerid.trim();
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage == null ? null : voltage.trim();
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature == null ? null : temperature.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}