package com.oaec.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.oaec.service.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

//房屋类型

@Controller
@RequestMapping("/type")
public class TypeController {
    @Resource
    private TypeService service;

    //跳转主页
    @RequestMapping("/toMain")
    public String toMain(){
        return "type";
    }


    //查询所有的房屋类型
    @RequestMapping(value = "/getAllType",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getAllType(String type_name,
                             Integer page,
                             Integer rows){

        Map<String,Object> param = new HashMap<>();
        param.put("type_name",type_name);
        param.put("pageNo",page==null?1:page);
        param.put("pageSize",rows==null?1000:rows);

        Map<String, Object> allStatus = service.findAllType(param);

        return JSON.toJSONString(allStatus);
    }

    //2. 添加或修改房屋类型
    @RequestMapping(value = "/saveOrUpdateType",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String saveOrUpdateType(String type_id,
                                   String type_name){

        Map<String,Object> map = new HashMap<>();
        map.put("type_id",type_id);
        map.put("type_name",type_name);
        int i = service.saveOrUpdateType(map);
        String res = i==1?"success":"error";
        JSONObject obj = new JSONObject();
        obj.put("info",res);
        return obj.toJSONString();
    }

    //假删
    @RequestMapping(value = "/deleteType", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deleteType(int type_id){

        boolean b = service.deleteType(type_id);
        String res = b == true ? "success":"error";
        JSONObject obj = new JSONObject();
        obj.put("info",res);
        return obj.toJSONString();
    }
}
