package com.yz.po;

public class Site {
    private Integer id;

    private String name;

    private String lng;

    private String lat;

    private String markid;

    private String snumber;

    private Integer viewstyle;

    private String areaname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public String getMarkid() {
        return markid;
    }

    public void setMarkid(String markid) {
        this.markid = markid == null ? null : markid.trim();
    }

    public String getSnumber() {
        return snumber;
    }

    public void setSnumber(String snumber) {
        this.snumber = snumber == null ? null : snumber.trim();
    }

    public Integer getViewstyle() {
        return viewstyle;
    }

    public void setViewstyle(Integer viewstyle) {
        this.viewstyle = viewstyle;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname == null ? null : areaname.trim();
    }
}