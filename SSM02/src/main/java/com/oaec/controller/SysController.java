package com.oaec.controller;

import com.alibaba.fastjson.JSONObject;
import com.oaec.service.SysService;
import com.oaec.util.ModuleUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

//用户
@Controller
@RequestMapping("/sys")
public class SysController {
    @Resource
    private SysService service;
    @Resource
    private ModuleUtil  util;

    //1.用户登录
    @RequestMapping(value = "/userLogin",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String userLogin(String username,
                            String password,
                            HttpSession session){
        List<Map<String, Object>> list = service.userLogin(username, password);
        JSONObject obj = new JSONObject();
        if(list!=null && list.size()>0){
            session.setAttribute("user_id",list.get(0).get("user_id"));
            session.setAttribute("username",list.get(0).get("username"));
            obj.put("info","success");
        }else{
            obj.put("info","error");
        }
        return obj.toJSONString();
    }


    //跳转页面
    @RequestMapping("/toHome")
    public String toHome(HttpSession session){
        //用户id
        int user_id=Integer.valueOf(session.getAttribute("user_id").toString());
        //从数据库查询当前用户的所有权限
        List<Map<String,Object>>auths=service.getAllAuthByUserId(user_id);
        //利用moduleUtil 把结果组织成easyui-tree所需要的格式
        List<Map<String,Object>> list=util.moduleUtil(auths);
        session.setAttribute("modules",list);
       /* JSONObject obj= new JSONObject();
        obj.put("data",list);
        System.out.println(obj.toString());*/
         /*查询资源权限*/
        List<Map<String, Object>> resources = service.getAllResourceByUserId(user_id);
        session.setAttribute("resources",resources);

        return "home";
    }


    //注销 退出
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login.jsp";
    }

}
