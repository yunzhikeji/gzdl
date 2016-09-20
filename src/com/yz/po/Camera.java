package com.yz.po;

public class Camera {
    private Integer id;

    private String sipid;

    private String sipserverid;

    private String lng;

    private String lat;

    private String cnumber;

    private String cname;

    private String voltage;

    private String temperature;

    private Integer status;

    private Integer iscontroll;

    private Integer stat;

    private Integer state;

    private Integer cameraid;

    private Integer organizeid;

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

    public Integer getIscontroll() {
        return iscontroll;
    }

    public void setIscontroll(Integer iscontroll) {
        this.iscontroll = iscontroll;
    }

    public Integer getStat() {
        return stat;
    }

    public void setStat(Integer stat) {
        this.stat = stat;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getCameraid() {
        return cameraid;
    }

    public void setCameraid(Integer cameraid) {
        this.cameraid = cameraid;
    }

    public Integer getOrganizeid() {
        return organizeid;
    }

    public void setOrganizeid(Integer organizeid) {
        this.organizeid = organizeid;
    }
}