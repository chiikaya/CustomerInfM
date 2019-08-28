package com.oaec.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.oaec.service.InfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//房屋信息

@Controller
@RequestMapping("/info")
public class InfoController {
    @Resource
    private InfoService service;

    //跳转主页
    @RequestMapping("/toMain")
    public String toMain(){
        return "Info";
    }


    //查询所有的房屋类型
    @RequestMapping(value = "/getAllInfo",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getAllInfo(String info_text ,
                             String info_where,
                             Integer page,
                             Integer rows){

        Map<String,Object> param = new HashMap<>();
        param.put("info_text",info_text);
        param.put("info_where",info_where);
        param.put("pageNo",page==null?1:page);
        param.put("pageSize",rows==null?1000:rows);

        Map<String, Object> allStatus = service.findAllInfo(param);
      //  System.out.println("信息：---------"+allStatus);
        return JSON.toJSONString(allStatus);
    }

    //2. 添加或修改房屋类型
    @RequestMapping(value = "/saveOrUpdateInfo",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String saveOrUpdateInfo(String info_id,
                                   String type_id,
                                   String staff_id,
                                   String info_adress,
                                   String info_price){

        Map<String,Object> map = new HashMap<>();
        map.put("info_id",info_id);
        map.put("type_id",type_id);
        map.put("staff_id",staff_id);
        map.put("info_adress",info_adress);
        map.put("info_price",info_price);
        int i = service.saveOrUpdateInfo(map);
        //System.out.println(i);
        String res = i==1?"success":"error";
        JSONObject obj = new JSONObject();
        obj.put("info",res);
        return obj.toJSONString();
    }

    //假删
    @RequestMapping(value = "/deleteInfo", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deleteInfo(int info_id){

        boolean b = service.deleteInfo(info_id);
        String res = b == true ? "success":"error";
        JSONObject obj = new JSONObject();
        obj.put("info",res);
        return obj.toJSONString();
    }

    //户型
    @RequestMapping(value = "/getType",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getType(){
        JSONArray jsonArray = new JSONArray();
        List<Map<String, Object>> hx = service.getType();
        jsonArray.addAll(hx);
        return jsonArray.toJSONString();
    }

    //管理员
    @RequestMapping(value = "/getDesc2",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getDesc2(){
        JSONArray jsonArray = new JSONArray();
        List<Map<String, Object>> gy = service.getDesc2();
        jsonArray.addAll(gy);
        return jsonArray.toJSONString();
    }


}
