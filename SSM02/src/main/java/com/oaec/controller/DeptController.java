package com.oaec.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.oaec.service.DeptService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

//部门
@Controller
@RequestMapping("/dept")
public class DeptController {

    @Resource
    private DeptService service;

    //跳转主页
    @RequestMapping("/toMain")
    public String toMain(){
        return "dept";
    }


    // 查询所有的部门
    @RequestMapping(value = "/getAllDept",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getAllDept(String dept_name,
                             String dept_desc,
                             int page,
                             int rows){

        Map<String,Object> param = new HashMap<>();
        param.put("dept_name",dept_name);
        param.put("dept_desc",dept_desc);
        param.put("pageNo",page);
        param.put("pageSize",rows);

        Map<String, Object> allDept = service.findAllDept(param);

        return JSON.toJSONString(allDept);
    }

    //2. 添加或修改部门
    @RequestMapping(value = "/saveOrUpdateDept",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String saveOrUpdateDept(String dept_id,
                                   String dept_name,
                                   String dept_desc,
                                   String dept_location,
                                   String inuse){
        Map<String,Object> map = new HashMap<>();
        map.put("dept_id",dept_id);
        map.put("dept_name",dept_name);
        map.put("dept_desc",dept_desc);
        map.put("dept_location",dept_location);
        map.put("inuse",inuse);
        int i = service.saveOrUpdateDept(map);
        String res = i==1?"success":"error";
        JSONObject obj = new JSONObject();
        obj.put("info",res);
        return obj.toJSONString();
    }

    //3. 删除部门
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/deleteDept", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deleteDept(int dept_id){

        int i = service.deleteDept(dept_id);
        String res = i == 1 ? "success":"error";
        JSONObject obj = new JSONObject();
        obj.put("info",res);
        return obj.toJSONString();
    }



}
