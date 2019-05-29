package com.cn.ntl.service.serviceimpl;

import com.cn.ntl.entity.*;
import com.cn.ntl.mapper.LoginMapper;
import com.cn.ntl.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceimpl implements LoginService {
    @Resource
    public LoginMapper loginMapper;

    @Override
    public boolean vipUpdate(vip vip) {
        Integer i=loginMapper.vipUpdate(vip);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<vip> selectVip(Map<String, Object> map) {
        return loginMapper.selectVip(map);
    }

    @Override
    public List<xiaoshou> selectXiaoshou(Map<String, Object> map) {
        return loginMapper.selectXiaoshou(map);
    }

    @Override
    public List<xiaoshou> selectXiaoshou1() {
        return loginMapper.selectXiaoshou1();
    }

    @Override
    public List<vip> selectVipVtel(vip v) {
        return loginMapper.selectVipVtel(v);
    }

    @Override
    public List<fangshi> selectFangshi() {
        return loginMapper.selectFangshi();
    }

    @Override
    public role login(role r) {
        return loginMapper.login(r);
    }

    @Override
    public vip selectVipId(String vid) {
        return loginMapper.selectVipId(vid);
    }

    @Override
    public boolean vipadd(vip v) {
        int i = loginMapper.vipadd(v);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int findCountXiaoshou() {
        return loginMapper.findCountXiaoshou();
    }

    @Override
    public int findCountXiaofei() {
        return loginMapper.findCountXiaofei();
    }

    @Override
    public boolean xiaofeiadd(xiaofei xiaofei) {
        int i = loginMapper.xiaofeiadd(xiaofei);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean selectVtel(vip v) {
       int i=loginMapper.selectVtel(v);
        if (i > 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean xiaoshouadd(xiaoshou xs) {
        int i=loginMapper.xiaoshouadd(xs);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean selectVtelxiaoshou(xiaoshou xs) {
        int i=loginMapper.selectVtelxiaoshou(xs);
        if (i > 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<Map<String, Object>> selectXiaofei(Map<String, Object> map) {
        return loginMapper.selectXiaofei(map);
    }

    @Override
    public List<Map<String, Object>> selectXiaofei1(Map<String, Object> map) {
        return loginMapper.selectXiaofei1(map);
    }

    @Override
    public int findCountVip() {
        return loginMapper.findCountVip();
    }

    @Override
    public List<Map<String,Object>> findCountXiaofei1(Map<String, Object> map) {
        return loginMapper.findCountXiaofei1(map);
    }

    @Override
    public List<Map<String, Object>> selectXiaofeiYeji(Map<String, Object> map) {
        return loginMapper.selectXiaofeiYeji(map);
    }

}
