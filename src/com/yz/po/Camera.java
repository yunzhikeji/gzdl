package com.yz.po;

public class Camera {
    private Integer id;

    private String sipid;//设备的sip编号

    private String sipserverid;//sip服务器编号

    private String lng;//经度

    private String lat;//纬度

    private String cnumber;//设备编号

    private String cname;//设备名称

    private String voltage;//设备电压

    private String temperature;//设备温度

    private Integer status;//设备工作状态  A=正常工作(1)，D=关机(0)，R=重启中(2)，N=未知状态(-1),也用于判断设备的GPS信号接收状态，详见mina相关代码

    private Integer iscontroll;//是否已经布控（0:未布控 1：已布控）（用于人脸布控）

    private Integer stat;//是否布控成功(0：未布控成功，1：已布控成功)（用于人脸布控）

    private Integer state;//摄像头sipid是否注册成功()

    private Integer cameraid;//设备的摄像头编号

    private Integer organizeid;//组织(施工单位)id

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

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cname == null) ? 0 : cname.hashCode());
		result = prime * result + ((cnumber == null) ? 0 : cnumber.hashCode());
		result = prime * result + ((organizeid == null) ? 0 : organizeid.hashCode());
		result = prime * result + ((sipid == null) ? 0 : sipid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Camera other = (Camera) obj;
		if (cname == null) {
			if (other.cname != null)
				return false;
		} else if (!cname.equals(other.cname))
			return false;
		if (cnumber == null) {
			if (other.cnumber != null)
				return false;
		} else if (!cnumber.equals(other.cnumber))
			return false;
		if (organizeid == null) {
			if (other.organizeid != null)
				return false;
		} else if (!organizeid.equals(other.organizeid))
			return false;
		if (sipid == null) {
			if (other.sipid != null)
				return false;
		} else if (!sipid.equals(other.sipid))
			return false;
		return true;
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

	@Override
	public String toString() {
		return "Camera [id=" + id + ", sipid=" + sipid + ", sipserverid=" + sipserverid + ", lng=" + lng + ", lat="
				+ lat + ", cnumber=" + cnumber + ", cname=" + cname + ", voltage=" + voltage + ", temperature="
				+ temperature + ", status=" + status + ", iscontroll=" + iscontroll + ", stat=" + stat + ", state="
				+ state + ", cameraid=" + cameraid + ", organizeid=" + organizeid + "]";
	}
    
    
    
    
}