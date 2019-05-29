package com.cn.ntl.mapper;

import com.cn.ntl.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface LoginMapper {
    public Integer vipUpdate(vip vip);
    public List<vip> selectVip(Map<String, Object> map);
    public List<vip> selectVipVtel(vip v);

    public List<xiaoshou> selectXiaoshou(Map<String, Object> map);
    public List<xiaoshou> selectXiaoshou1();
    public List<fangshi> selectFangshi();
    public role login(role r);
    public vip selectVipId(String vid);
    public List<Map<String,Object>> selectXiaofei(Map<String, Object> map);
    public List<Map<String,Object>> selectXiaofeiYeji(Map<String, Object> map);
    public List<Map<String,Object>> selectXiaofei1(Map<String, Object> map);
    int findCountVip();
    int findCountXiaoshou();
    int findCountXiaofei();
    List<Map<String,Object>> findCountXiaofei1(Map<String, Object> map);
    int vipadd(vip v);
    int xiaofeiadd(xiaofei xiaofei);
    int selectVtel(vip v);
    int xiaoshouadd(xiaoshou xs);
    int selectVtelxiaoshou(xiaoshou xs);
}
