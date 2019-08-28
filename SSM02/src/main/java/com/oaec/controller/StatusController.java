package com.oaec.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.oaec.service.StatusService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//客户状态

@Controller
@RequestMapping("/status")
public class StatusController {

    @Resource
    private StatusService service;

    //跳转主页
    @RequestMapping("/toMain")
    public String toMain(){
        return "status";
    }


    //查询所有的客户状态
    @RequestMapping(value = "/getAllStatus",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getAllStatus(String status_inuse,
                              Integer page,
                              Integer rows){


        Map<String,Object> param = new HashMap<>();
        param.put("status_inuse",status_inuse);
        param.put("pageNo",page==null?1:page);
        param.put("pageSize",rows==null?1000:rows);

        Map<String, Object> allStatus = service.findAllStatus(param);

        return JSON.toJSONString(allStatus);
    }

    //2. 添加或修改客户关怀
    @RequestMapping(value = "/saveOrUpdateStatus",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String saveOrUpdateStatus(String status_id,
                                   String status_inuse,
                                   String status_desc){

        Map<String,Object> map = new HashMap<>();
        map.put("status_id",status_id);
        map.put("status_inuse",status_inuse);
        map.put("status_desc",status_desc);
        int i = service.saveOrUpdateStatus(map);
        String res = i==1?"success":"error";
        JSONObject obj = new JSONObject();
        obj.put("info",res);
        return obj.toJSONString();
    }

    //假删
    @RequestMapping(value = "/deleteStatus", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deleteStatus(int status_id){

        boolean b = service.deleteStatus(status_id);
        String res = b == true ? "success":"error";
        JSONObject obj = new JSONObject();
        obj.put("info",res);
        return obj.toJSONString();
    }
}
