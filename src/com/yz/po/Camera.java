package com.yz.po;

public class Camera {
    private Integer id;

    private String sipid;

    private String sipserverid;

    private Double lng;

    private Double lat;

    private String cnumber;

    private String name;

    private String markerid;

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

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getCnumber() {
        return cnumber;
    }

    public void setCnumber(String cnumber) {
        this.cnumber = cnumber == null ? null : cnumber.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMarkerid() {
        return markerid;
    }

    public void setMarkerid(String markerid) {
        this.markerid = markerid == null ? null : markerid.trim();
    }
}