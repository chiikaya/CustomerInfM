package com.oaec.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.oaec.service.FromService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

//客户来源

@Controller
@RequestMapping("/from")
public class FromController {
    @Resource
    private FromService service;

    //跳转主页
    @RequestMapping("/toMain")
    public String toMain(){
        return "from";
    }


    //查询所有的客户来源
    @RequestMapping(value = "/getAllFrom",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getAllFrom(String from_name,
                               Integer page,
                               Integer rows){

        Map<String,Object> param = new HashMap<>();
        param.put("from_name",from_name);
        param.put("pageNo",page==null?1:page);
        param.put("pageSize",rows==null?1000:rows);

        Map<String, Object> allStatus = service.findAllFrom(param);

        return JSON.toJSONString(allStatus);
    }

    //2. 添加或修改客户来源
    @RequestMapping(value = "/saveOrUpdateFrom",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String saveOrUpdateFrom(String from_id,
                                     String from_name){

        Map<String,Object> map = new HashMap<>();
        map.put("from_id",from_id);
        map.put("from_name",from_name);
        int i = service.saveOrUpdateFrom(map);
        String res = i==1?"success":"error";
        JSONObject obj = new JSONObject();
        obj.put("info",res);
        return obj.toJSONString();
    }

    //假删
    @RequestMapping(value = "/deleteFrom", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deleteFrom(int from_id){
        boolean b =  service.deleteFrom(from_id);
        String res = b == true ? "success":"error";
        JSONObject obj = new JSONObject();
        obj.put("info",res);
        return obj.toJSONString();
    }
}
