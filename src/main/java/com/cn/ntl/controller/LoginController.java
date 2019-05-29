package com.cn.ntl.controller;

import com.cn.ntl.entity.*;
import com.cn.ntl.service.LoginService;
import com.cn.ntl.utils.PageUtils;
import com.cn.ntl.utils.UUIDUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static java.lang.Double.parseDouble;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

@Controller
public class LoginController{
    @Resource
    public LoginService loginService;
    //index页面
    @RequestMapping("/index")
    public String index(Model model){

        return "index";
    }
    //会员查询
    @RequestMapping("/vip")
    public String vip(Model model, PageUtils p, vip v){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("number", (p.getCurrPage() - 1) * p.getRows());
            map.put("pageSize", p.getRows());
            map.put("u", v);

            p.setTotalNumber(loginService.findCountVip());

            if (p.getCurrPage() > p.getTotalPage()) {
                p.setCurrPage(1);
            }
            List<vip> listvip=loginService.selectVip(map);
            model.addAttribute("vip",listvip);
            model.addAttribute("p",p);
            model.addAttribute("u",v);
            return "vipselect";

    }
    @RequestMapping("/viptel")
    public String viptel(Model model, vip v){
        /*if(v.getVtel().equals("")){
            model.addAttribute("message","电话号不能为空！");
            return "vipselect";
        }else{*/
            List<vip> listvip=loginService.selectVipVtel(v);
            model.addAttribute("vip",listvip);
            return "telSelect";
       /* }*/

    }
    @RequestMapping("/vipVid")
    public String vipVid(Model model,vip v){
            String vid=v.getVid();
            vip listvip=loginService.selectVipId(vid);
                model.addAttribute("vip",listvip);
                return "telSelect";
    }
    //销售员查询
    @RequestMapping("/xiaoshou")
    public String xiaoshou(Model model, PageUtils p, xiaoshou x){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("number", (p.getCurrPage() - 1) * p.getRows());
        map.put("pageSize", p.getRows());
        map.put("u", x);

        p.setTotalNumber(loginService.findCountXiaoshou());

        if (p.getCurrPage() > p.getTotalPage()) {
            p.setCurrPage(1);
        }
        List<xiaoshou> listxiao=loginService.selectXiaoshou(map);
        model.addAttribute("xiao",listxiao);
        model.addAttribute("p",p);
        model.addAttribute("u",x);
        return "xiaoshouselect";
    }
    //vip充值
    @RequestMapping("/vipchong")
    public String vipchong(Model model,String vid){
        vip v=loginService.selectVipId(vid);
        model.addAttribute("v",v);
        return "vipchongzhi";
    }
    @RequestMapping("/vipchongzhi")
    public String vipchongzhi(Model model,vip vip,Double v1,Double v2){
        Double v=v1+v2;
        System.out.println(v);
        vip.setVbalance(v);
        boolean i=loginService.vipUpdate(vip);
        if(i==true){
            return "redirect:vip";
        }else {
            return "redirect:vip";
        }

    }
    //消费者查询
    @RequestMapping("/xiaofeizhe")
    public String xiaofeizhe(Model model,String vid,Integer m){
        vip v=loginService.selectVipId(vid);
        List<xiaoshou> x=loginService.selectXiaoshou1();
        List<fangshi> f=loginService.selectFangshi();
        model.addAttribute("vip",v);
        model.addAttribute("xiaoshou",x);
        model.addAttribute("fang",f);
        if(m==null){
            return "xiaofei";
        }else if(m==2){
            model.addAttribute("message","积分不足！");
            return "xiaofei";
        }else if(m==1){
            model.addAttribute("message","余额不足！");
            return "xiaofei";
        }
        return "xiaofei";
    }
    @RequestMapping("/xf")
    public String xf(Model model,String fvid,vip vip,Double vbalance2,Double vintegral2,Double fnum1,xiaofei xiaofei){
        double vv1=vbalance2;//消费前余额
        double vv2=vintegral2;//消费前积分
        Double vv3=fnum1;//消费金额
        if(xiaofei.getFangshi().equals("1")){
            //余额消费
            if(vv1>=vv3){
                //消费后金额
                Double v=vv1-vv3;
                //消费后得积分
                double v4;
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                int month=cal.get(Calendar.MONTH);//获取月份
                int day=cal.get(Calendar.DATE);//获取日

                Calendar cal2 = Calendar.getInstance();
                cal2.setTime(vip.getVbrith());
                int month2=cal2.get(Calendar.MONTH);//获取月份
                int day2=cal2.get(Calendar.DATE);//获取日
                if (month==month2 && day==day2){
                    v4=vv2+(vv3/10)*2;
                }else {
                    v4=vv2+vv3/10;
                }
                vip.getVbrith();
                vip.setVbalance(v);
                vip.setVintegral(v4);
                vip.setVid(fvid);
                xiaofei.setFnum(fnum1);
                String id =UUIDUtil.getUUID();
                xiaofei.setFid(id);
                Date date=new Date();
                xiaofei.setFtime(date);
                boolean i=loginService.xiaofeiadd(xiaofei);
                boolean i1=loginService.vipUpdate(vip);
                return "redirect:vip";
            }else{
                int m=1;
                /*model.addAttribute("message","余额不足！");*/
                return "redirect:xiaofeizhe?vid="+fvid+"&m="+m;
            }
        }else if(xiaofei.getFangshi().equals("2")){//积分消费
            if(vv2>=vv3){
                //消费后积分
                double v=(vv2-vv3);
                vip.setVid(fvid);
                vip.setVintegral(v);
                xiaofei.setFnum(fnum1);
                String id =UUIDUtil.getUUID();
                xiaofei.setFid(id);
                Date date=new Date();
                xiaofei.setFtime(date);
                boolean i=loginService.xiaofeiadd(xiaofei);
                boolean i1=loginService.vipUpdate(vip);
                return "redirect:vip";
            }else{
                int m=2;
                /*model.addAttribute("message","积分不足！");*/
                return "redirect:xiaofeizhe?vid="+fvid+"&m="+m;
            }
        }
        return "xiaofei";
    }
    //消费记录
    @RequestMapping("/xiaofeijilu")
    public String selectXiaofei(Model model,PageUtils p){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("number", (p.getCurrPage() - 1) * p.getRows());
        map.put("pageSize", p.getRows());
        /*map.put("u", x);*/

        p.setTotalNumber(loginService.findCountXiaofei());

        if (p.getCurrPage() > p.getTotalPage()) {
            p.setCurrPage(1);
        }
        List<Map<String,Object>> xiaofei=loginService.selectXiaofei(map);
        model.addAttribute("xiaofei",xiaofei);
        model.addAttribute("p",p);
        return "xiaofeiselect";
    }
    //消费记录
    @RequestMapping("/xiaofeijilu1")
    public String selectXiaofei(Model model,PageUtils p,Integer moe){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("number", (p.getCurrPage() - 1) * p.getRows());
        map.put("pageSize", p.getRows());
        map.put("moe",moe);
        /*map.put("u", x);*/
        List<Map<String,Object>> li=loginService.findCountXiaofei1(map);
        int num=li.size();
        p.setTotalNumber(num);

        if (p.getCurrPage() > p.getTotalPage()) {
            p.setCurrPage(1);
        }
        List<Map<String,Object>> xiaofei=loginService.selectXiaofei1(map);
        model.addAttribute("xiaofei",xiaofei);
        model.addAttribute("p",p);
        model.addAttribute("moe",moe);
        return "anyue";
    }
    @RequestMapping("/xiaofeijiluyeji")
    public String selectXiaofeiYeji(Model model,PageUtils p,Integer moe){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("moe",moe);
        List<Map<String,Object>> xiaofei=loginService.selectXiaofeiYeji(map);
        model.addAttribute("xiaofei",xiaofei);
        model.addAttribute("moe",moe);
        return "yeji";
    }
    @RequestMapping("/login")
    public String login(role r, Model model){
            role user=loginService.login(r);
            if(user==null){
                model.addAttribute("message", "用户不存在！");
                return "login";
            }else if(!user.getRpassword().equals(r.getRpassword())){
                model.addAttribute("message", "密码错误！");
                return "login";
            }else{
                /*redirect:findAllUsersByPage*/
                return "index";
            }
    }
    @RequestMapping("/toAdd")
    public String add(){
        return "vipadd";
    }
    @RequestMapping("/add")
    public String add(Model model, vip v){
        boolean i=loginService.selectVtel(v);
        if(i==false){
            model.addAttribute("message", "此手机号已存在！");
            return "vipadd";
        }else{
            String id =UUIDUtil.getUUID();
            v.setVid(id);
            boolean add = loginService.vipadd(v);

            if (add == false) {
                model.addAttribute("message", "添加失败！");
                return "vipadd";
            }
            return "index";
        }


    }


    @RequestMapping("/xiaoshouAdd")
    public String addXiaoshou(){
        return "xiaoshouadd";
    }
    @RequestMapping("/txiaoshouadd")
    public String addXiaoshou(Model model, xiaoshou xs){
        boolean i=loginService.selectVtelxiaoshou(xs);
        if(i==false){
            model.addAttribute("message", "此手机号已存在！");
            return "xiaoshouadd";
        }else{
            String id =UUIDUtil.getUUID();
            xs.setXid(id);
            boolean add = loginService.xiaoshouadd(xs);

            if (add == false) {
                model.addAttribute("message", "添加失败！");
                return "xiaoshouadd";
            }
            return "index";
        }


    }
}
