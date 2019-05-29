package com.cn.ntl.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class xiaofei {
    private String fid;
    private String fvid;
    private String fxid;
    private Double fnum;
    private String fangshi;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ftime;

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFvid() {
        return fvid;
    }

    public void setFvid(String fvid) {
        this.fvid = fvid;
    }

    public String getFxid() {
        return fxid;
    }

    public void setFxid(String fxid) {
        this.fxid = fxid;
    }

    public Double getFnum() {
        return fnum;
    }

    public void setFnum(Double fnum) {
        this.fnum = fnum;
    }

    public String getFangshi() {
        return fangshi;
    }

    public void setFangshi(String fangshi) {
        this.fangshi = fangshi;
    }

    public Date getFtime() {
        return ftime;
    }

    public void setFtime(Date ftime) {
        this.ftime = ftime;
    }
}
