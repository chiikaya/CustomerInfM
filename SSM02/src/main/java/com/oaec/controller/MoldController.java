package com.oaec.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.oaec.service.FromService;
import com.oaec.service.MoldService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

//客户类型

@Controller
@RequestMapping("/mold")
public class MoldController {
    @Resource
    private MoldService service;

    //跳转主页
    @RequestMapping("/toMain")
    public String toMain(){
        return "mold";
    }

    //查询所有的客户类型
    @RequestMapping(value = "/getAllMold",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getAllMold(String mold_name,
                               Integer page,
                               Integer rows){

        Map<String,Object> param = new HashMap<>();
        param.put("mold_name",mold_name);
        param.put("pageNo",page==null?1:page);
        param.put("pageSize",rows==null?1000:rows);

        Map<String, Object> allStatus = service.findAllMold(param);

        return JSON.toJSONString(allStatus);
    }

    //2. 添加或修改客户来源
    @RequestMapping(value = "/saveOrUpdateMold",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String saveOrUpdateMold(String mold_id,
                                   String mold_name){

        Map<String,Object> map = new HashMap<>();
        map.put("mold_id",mold_id);
        map.put("mold_name",mold_name);
        int i = service.saveOrUpdateMold(map);
        String res = i==1?"success":"error";
        JSONObject obj = new JSONObject();
        obj.put("info",res);
        return obj.toJSONString();
    }

    //假删
    @RequestMapping(value = "/deleteMold", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deleteMold(int mold_id){

        boolean b = service.deleteMold(mold_id);
        String res = b == true ? "success":"error";
        JSONObject obj = new JSONObject();
        obj.put("info",res);
        return obj.toJSONString();
    }
}
