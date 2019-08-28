package com.oaec.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.oaec.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//客户
@Controller
@RequestMapping("/client")
public class ClientController {

    @Resource
    private ClientService service;


    //跳转主页
    @RequestMapping("/toMain")
    public String toMain(){
        return "client";
    }


    @RequestMapping(value = "/getAllClient",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getAllClient(String find_text,
                                String find_where,
                             int page,
                             int rows){

        Map<String,Object> param = new HashMap<>();
        param.put("find_text",find_text);
        param.put("find_where",find_where);
        param.put("pageNo",page);
        param.put("pageSize",rows);

        Map<String, Object> allDept = service.findAllClient(param);

        return JSON.toJSONString(allDept);
    }

    //2. 添加或修改客户信息
    @RequestMapping(value = "/saveOrUpdateClient",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String saveOrUpdateClient(String client_id,
                                   String clientname,
                                   String status_inuse,
                                   String from_name,
                                   String staff_name,
                                   String mold_name,
                                   String sex,
                                   String tel,
                                   String QQ,
                                   String email,
                                   String post,
                                   String company ){
        Map<String,Object> map = new HashMap<>();
        map.put("client_id",client_id);
        map.put("clientname",clientname);
        map.put("status_id",status_inuse);
        map.put("from_id",from_name);
        map.put("staff_id",staff_name);
        map.put("mold_id",mold_name);
        map.put("sex",sex);
        map.put("tel",tel);
        map.put("QQ",QQ);
        map.put("email",email);
        map.put("post",post);
        map.put("company",company);
       // System.out.println("客户信息：----------------"+map);
        int i = service.saveOrUpdateClient(map);
        System.out.println(i);
        String res = i==1?"success":"error";
        JSONObject obj = new JSONObject();
        obj.put("info",res);
        return obj.toJSONString();
    }

    //假删客户
    @RequestMapping(value = "/deleteClient", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deleteClient(int client_id){

        boolean b = service.deleteClient(client_id);
        String res = b == true ? "success":"error";
        JSONObject obj = new JSONObject();
        obj.put("info",res);
        return obj.toJSONString();
    }







/*   ---------------获取下拉列表---------------------------*/
    //客户状态
    @RequestMapping(value = "/getStatus",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getStatus(){
        JSONArray jsonArray = new JSONArray();
        List<Map<String, Object>> khzt = service.getStatus();
        jsonArray.addAll(khzt);
        return jsonArray.toJSONString();
    }

    //客户来源
    @RequestMapping(value = "/getFrom",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getFrom(){
        JSONArray jsonArray = new JSONArray();
        List<Map<String, Object>> khly = service.getFrom();
        jsonArray.addAll(khly);
        return jsonArray.toJSONString();
    }

    //所属员工
    @RequestMapping(value = "/getStaff",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getStaff(){
        JSONArray jsonArray = new JSONArray();
        List<Map<String, Object>> yg = service.getStaff();
        jsonArray.addAll(yg);
        return jsonArray.toJSONString();
    }


    //客户类型
    @RequestMapping(value = "/getMold",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getMold(){
        JSONArray jsonArray = new JSONArray();
        List<Map<String, Object>> khlx = service.getMold();
        jsonArray.addAll(khlx);
        return jsonArray.toJSONString();
    }



}
