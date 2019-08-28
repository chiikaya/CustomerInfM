package com.oaec.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.oaec.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 联系人
@Controller
@RequestMapping("/person")
public class PersonController {

    @Resource
    private PersonService service;

    //跳转主页
    @RequestMapping("/toMain")
    public String toMain() {
        return "person";
    }

    //1.查询所有的联系记录
    @RequestMapping(value = "/getAllPerson", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getAllPerson(String person_text,
                               String person_way,
                               int page,
                               int rows) {

        Map<String, Object> param = new HashMap<>();
        param.put("person_text", person_text);
        param.put("person_way", person_way);
        param.put("pageNo", page);
        param.put("pageSize", rows);

        Map<String, Object> allThought = service.findAllPerson(param);

        return JSON.toJSONString(allThought);
    }

    //2. 添加或修改联系记录
       /*person_id,person_customer,person_name,person_sex,person_age,person_post,person_tel,customer_nexus*/
    @RequestMapping(value = "/saveOrUpdatePerson",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String saveOrUpdatePerson(String person_id,
                                     String person_customer,
                                     String person_name,
                                     String person_sex,
                                     String person_age,
                                     String person_post,
                                     String person_tel,
                                     String customer_nexus){
        Map<String,Object> map = new HashMap<>();
        map.put("person_id",person_id);
        map.put("person_customer",person_customer);
        map.put("person_name",person_name);
        map.put("person_sex",person_sex);
        map.put("person_age",person_age);
        map.put("person_post",person_post);
        map.put("person_tel",person_tel);
        map.put("customer_nexus",customer_nexus);
        int i = service.saveOrUpdatePerson(map);
        String res = i==1?"success":"error";
        JSONObject obj = new JSONObject();
        obj.put("info",res);
        return obj.toJSONString();
    }


    //3. 删除客户
    @RequestMapping(value="/deletePerson",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deletePerson(int person_id){
        boolean b = service.deletePerson(person_id);
        JSONObject object=new JSONObject();
        object.put("info",b?"success":"error");
        return object.toJSONString();
    }

    //*   ---------------获取下拉列表---------------------------*/
    //客户
    @RequestMapping(value = "/getPersonKH",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getPersonKH(){
        JSONArray jsonArray = new JSONArray();
        List<Map<String, Object>> kh2 = service.getPersonKH();
        jsonArray.addAll(kh2);
        return jsonArray.toJSONString();
    }

}

