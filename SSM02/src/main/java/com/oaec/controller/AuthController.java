package com.oaec.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.oaec.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//权限
@Controller
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private AuthService service;

    // 跳转主页
    @RequestMapping("/toMain")
    public String toMain(){
        return "auth";
    }

    //1.查询所有权限
    @RequestMapping(value="/getAllAuth",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getAllAuth(){
        List<Map<String, Object>> allAuth = service.getAllAuth();
        return JSON.toJSONString(allAuth);
    }

    //2. 添加或修改权限
    @RequestMapping(value = "/saveOrUpdateAuth",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String saveOrUpdateAuth(String auth_id,
                                   String auth_name,
                                   String parent_id,
                                   String auth_desc,
                                   String auth_type,
                                   String auth_order,
                                   String auth_layer,
                                   String auth_url,
                                   String inuse){
        Map<String,Object> map = new HashMap<>();
        map.put("auth_id",auth_id);
        map.put("auth_name",auth_name);
        map.put("parent_id",parent_id);
        map.put("auth_desc",auth_desc);
        map.put("auth_type",auth_type);
        map.put("auth_order",auth_order);
        map.put("auth_layer",auth_layer);
        map.put("auth_url",auth_url);
        map.put("inuse",inuse);
        int i = service.saveOrUpdateAuth(map);
        String res = i==1?"success":"error";
        JSONObject obj = new JSONObject();
        obj.put("info",res);
        return obj.toJSONString();
    }
}
