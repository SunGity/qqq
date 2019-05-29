package com.cn.ntl.service;

import com.cn.ntl.entity.*;

import java.util.List;
import java.util.Map;

public interface LoginService {
    public boolean vipUpdate(vip vip);
    public List<vip> selectVip(Map<String, Object> map);
    public List<xiaoshou> selectXiaoshou(Map<String, Object> map);
    public List<xiaoshou> selectXiaoshou1();
    public List<vip> selectVipVtel(vip v);
    public List<fangshi> selectFangshi();
    public role login(role r);
    public vip selectVipId(String vid);
    boolean vipadd(vip v);
    int findCountXiaoshou();
    int findCountXiaofei();
    boolean xiaofeiadd(xiaofei xiaofei);
    boolean selectVtel(vip v);
    boolean xiaoshouadd(xiaoshou xs);
    boolean selectVtelxiaoshou(xiaoshou xs);
    public List<Map<String,Object>> selectXiaofei(Map<String, Object> map);
    public List<Map<String,Object>> selectXiaofei1(Map<String, Object> map);
    int findCountVip();
    List<Map<String,Object>> findCountXiaofei1(Map<String, Object> map);
    public List<Map<String,Object>> selectXiaofeiYeji(Map<String, Object> map);
}
