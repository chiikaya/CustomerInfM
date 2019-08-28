package com.oaec.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.oaec.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService service;

   //跳转首页
    @RequestMapping("/toMain")
    public String toMain(){
        return "user";
    }


    // 查询所有用户
    @RequestMapping(value = "/getAllUser",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getAllRole(String username,
                             int page,
                             int rows){

        Map<String,Object> param = new HashMap<>();
        param.put("username",username);
        param.put("pageNo",page);
        param.put("pageSize",rows);

        Map<String, Object> allRole = service.findAllUser(param);

        return JSON.toJSONString(allRole);
    }


    //2. 添加或修改权限
    @RequestMapping(value = "/saveOrUpdateUser",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String saveOrUpdateUser(String user_id,
                                   String username,
                                   String password,
                                   String tel,
                                   String email,
                                   String dept_id,
                                   String inuse){
        Map<String,Object> map = new HashMap<>();
        map.put("user_id",user_id);
        map.put("username",username);
        map.put("password",password);
        map.put("tel",tel);
        map.put("email",email);
        map.put("dept_id",dept_id);
        map.put("inuse",inuse);
        int i = service.saveOrUpdateUser(map);
        String res = i==1?"success":"error";
        JSONObject obj = new JSONObject();
        obj.put("info",res);
        return obj.toJSONString();
    }

    /*角色授权窗口 查询数据
      * 1.所有权限
      * 2. 当前角色所具有的权限*/
    @RequestMapping(value="/getRoleByUserId",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getRoleByUserId(int user_id){
        List<Map<String, Object>> list = service.getRoleByUserId(user_id);
        return JSON.toJSONString(list);
    }


    //保存修改之后的权限
    /*SpringMVC 接收数据参数
    * 1. ajax 中添加一个属性：  traditional:true  array=1&array=2
    *2.如下
    *3.使用request作为入参，req.getParameterValues("array")
    *  required = false ：允许授权为空
    * */
    @RequestMapping(value="/saveRoleOfUser",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String saveRoleOfUser(int user_id,
                                 @RequestParam(value="array[]",required = false )int[] array){
        boolean b = service.saveRoleOfUser(user_id, array);
        JSONObject object=new JSONObject();
        object.put("info",b?"success":"error");
        return object.toJSONString();
    }

    //删除用户
    @RequestMapping(value = "/deleteUser", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deleteUser(int user_id) {
        boolean b = service.deleteUser(user_id);
        JSONObject obj = new JSONObject();
        obj.put("info", b ? "success" : "error");
        return obj.toJSONString();
    }

    //保存部门
    @RequestMapping(value = "/saveDptOfUser", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String saveDptOfUser(int user_id, int dept_id) {
        System.out.println("进来了");
        Map<String, Integer> map = new HashMap<>();

        map.put("user_id", user_id);
        map.put("dept_id", dept_id);

        boolean b = service.saveDptOfUser(map);
        JSONObject obj = new JSONObject();
        obj.put("info", b ? "success" : "error");
        return obj.toJSONString();
    }

    //根据角色id查询部门
    @RequestMapping(value = "/changeUserDept", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String changeUserDpt(int user_id) {
        List<Map<String, Object>> list = service.getDptByUserId(user_id);
        return JSON.toJSONString(list);
    }

    //部门
    @RequestMapping(value = "/getUSDept",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getUSDept(){
        JSONArray jsonArray = new JSONArray();
        List<Map<String, Object>> bu= service.getUSDept();
        jsonArray.addAll(bu);
        return jsonArray.toJSONString();
    }
}
