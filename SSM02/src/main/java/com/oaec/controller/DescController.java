package com.oaec.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.oaec.service.DescService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//员工信息
@Controller
@RequestMapping("/desc")
public class DescController {

    @Resource
    private DescService service;

    //跳转主页
    @RequestMapping("/toMain")
    public String toMain(){
        return "desc";
    }


    @RequestMapping(value = "/getAllDesc",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getAllDesc(String desc_text4,
                             String desc_where4,
                               int page,
                               int rows){

        Map<String,Object> param = new HashMap<>();
        param.put("desc_text4",desc_text4);
        param.put("desc_where4",desc_where4);
        param.put("pageNo",page);
        param.put("pageSize",rows);


        Map<String, Object> allDept = service.findAllDesc(param);
       // System.out.println("员工信息：---------"+allDept);
        return JSON.toJSONString(allDept);
    }

    //2. 添加或修改员工信息
    @RequestMapping(value = "/saveOrUpdateDesc",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String saveOrUpdateDesc(String staff_id,
                                     String staff_name,
                                     String staff_age,
                                     String staff_sex,
                                     String staff_nation,
                                     String staff_dept,
                                     String staff_role,
                                     String staff_degree,
                                     String staff_marital,
                                     String staff_address,
                                     String staff_phone,
                                     String staff_tel,
                                     String staff_email ){
        Map<String,Object> map = new HashMap<>();
        map.put("staff_id",staff_id);
        map.put("staff_name",staff_name);
        map.put("staff_age",staff_age);
        map.put("staff_sex",staff_sex);
        map.put("staff_nation",staff_nation);
        map.put("staff_dept",staff_dept);
        map.put("staff_role",staff_role);
        map.put("staff_degree",staff_degree);
        map.put("staff_marital",staff_marital);
        map.put("staff_address",staff_address);
        map.put("staff_phone",staff_phone);
        map.put("staff_tel",staff_tel);
        map.put("staff_email",staff_email);
        System.out.println("员工信息：----------------"+map);
        int i = service.saveOrUpdateDesc(map);
        //System.out.println(i);
        String res = i==1?"success":"error";
        JSONObject obj = new JSONObject();
        obj.put("info",res);
        return obj.toJSONString();
    }

    //假删客户
    @RequestMapping(value = "/deleteDesc", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deleteDesc(int staff_id){
        boolean b = service.deleteDesc(staff_id);
        String res = b == true ? "success":"error";
        JSONObject obj = new JSONObject();
        obj.put("info",res);
        return obj.toJSONString();
    }


    /*   ---------------获取下拉列表---------------------------*/
    //部门
    @RequestMapping(value = "/getDept",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getDept(){
        JSONArray jsonArray = new JSONArray();
        List<Map<String, Object>> bumen = service.getDept();
        jsonArray.addAll(bumen);
        return jsonArray.toJSONString();
    }

    //角色
    @RequestMapping(value = "/getRole",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getRole(){
        JSONArray jsonArray = new JSONArray();
        List<Map<String, Object>> juese = service.getRole();
        jsonArray.addAll(juese);
        return jsonArray.toJSONString();
    }
}
