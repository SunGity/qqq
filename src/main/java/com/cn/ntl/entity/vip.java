package com.cn.ntl.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class vip {
    private String vid;
    private String vname;
    private String vtel;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date vbrith;
    private Double vintegral;
    private Double vbalance;

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public String getVtel() {
        return vtel;
    }

    public void setVtel(String vtel) {
        this.vtel = vtel;
    }

    public Date getVbrith() {
        return vbrith;
    }

    public void setVbrith(Date vbrith) {
        this.vbrith = vbrith;
    }

    public Double getVintegral() {
        return vintegral;
    }

    public void setVintegral(Double vintegral) {
        this.vintegral = vintegral;
    }

    public Double getVbalance() {
        return vbalance;
    }

    public void setVbalance(Double vbalance) {
        this.vbalance = vbalance;
    }
}
