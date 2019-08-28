package com.oaec.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.oaec.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//角色
@Controller
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService service;

    //跳转主页
    @RequestMapping("/toMain")
    public String toMain(){
        return "role";
    }


    //查询所有的角色
    @RequestMapping(value = "/getAllRole",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getAllRole(String role_name,
                             String role_desc,
                             int page,
                             int rows){

        Map<String,Object> param = new HashMap<>();
        param.put("role_name",role_name);
        param.put("role_desc",role_desc);
        param.put("pageNo",page);
        param.put("pageSize",rows);

        Map<String, Object> allRole = service.findAllRole(param);

        return JSON.toJSONString(allRole);
    }


    //2. 添加或修改角色
    @RequestMapping(value = "/saveOrUpdateRole",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String saveOrUpdateAuth(String role_id,
                                   String role_name,
                                   String role_desc,
                                   String role_order,
                                   String inuse){
        Map<String,Object> map = new HashMap<>();
        map.put("role_id",role_id);
        map.put("role_name",role_name);
        map.put("role_desc",role_desc);
        map.put("role_order",role_order);
        map.put("inuse",inuse);
        int i = service.saveOrUpdateRole(map);
        String res = i==1?"success":"error";
        JSONObject obj = new JSONObject();
        obj.put("info",res);
        return obj.toJSONString();
    }


    /*角色授权窗口 查询数据
    * 1.所有权限
    * 2. 当前角色所具有的权限*/
    @RequestMapping(value="/getAuthByRoleId",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getAuthByRoleId(int role_id){
        List<Map<String, Object>> list = service.getAuthByRoleId(role_id);
        return JSON.toJSONString(list);
    }


    //保存修改之后的权限
    /*SpringMVC 接收数据参数
    * 1. ajax 中添加一个属性：  traditional:true  array=1&array=2
    *2.如下
    *3.使用request作为入参，req.getParameterValues("array")
    *  required = false ：允许授权为空
    * */
    @RequestMapping(value="/saveAuthOfRole",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String saveAuthOfRole(int role_id,
                                 @RequestParam(value="array[]",required = false )int[] array){
        boolean b = service.saveAuthOfRole(role_id, array);
        JSONObject object=new JSONObject();
        object.put("info",b?"success":"error");
        return object.toJSONString();
    }

    //删除角色
    @RequestMapping(value="/deleteRole",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deleteRole(int role_id){
        boolean b = service.deleteRole(role_id);
        JSONObject object=new JSONObject();
        object.put("info",b?"success":"error");
        return object.toJSONString();
    }

}
