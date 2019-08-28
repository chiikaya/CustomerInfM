package com.oaec.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.oaec.service.ThoughtService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//客户关怀
@Controller
@RequestMapping("/thought")
public class ThoughtController {


    @Resource
    private ThoughtService service;

    //跳转主页
    @RequestMapping("/toMain")
    public String toMain(){
        return "thought";
    }

    //1.查询所有的客户关怀
    @RequestMapping(value = "/getAllThought",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getAllThought(String thought_text,
                                String thought_way,
                             int page,
                             int rows){

        Map<String,Object> param = new HashMap<>();
        param.put("thought_text",thought_text);
        param.put("thought_way",thought_way);
        param.put("pageNo",page);
        param.put("pageSize",rows);

        Map<String, Object> allThought = service.findAllThought(param);

        return JSON.toJSONString(allThought);
    }

    // thought_id,thought_name,thought_text,thought_way,create_time,reply_time,thought_desc,person
    //2. 添加或修改客户关怀
    @RequestMapping(value = "/saveOrUpdateThought",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String saveOrUpdateThought(String thought_id,
                                   String client_id,
                                   String thought_text,
                                   String thought_way,
                                   String create_time2,
                                   String reply_time2,
                                   String thought_desc,
                                   String person){
        Map<String,Object> map = new HashMap<>();
        map.put("thought_id",thought_id);
        map.put("client_id",client_id);
        map.put("thought_text",thought_text);
        map.put("thought_way",thought_way);
        map.put("create_time",create_time2);
        map.put("reply_time",reply_time2);
        map.put("thought_desc",thought_desc);
        map.put("person",person);
        int i = service.saveOrUpdateThought(map);
        String res = i==1?"success":"error";
        JSONObject obj = new JSONObject();
        obj.put("info",res);
        return obj.toJSONString();
    }


    //3. 删除客户
    @RequestMapping(value="/deleteThought",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deleteThought(int thought_id){
        boolean b = service.deleteThought(thought_id);
        JSONObject object=new JSONObject();
        object.put("info",b?"success":"error");
        return object.toJSONString();
    }





    //*   ---------------获取下拉列表---------------------------*/
    //客户
    @RequestMapping(value = "/getKH",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getKH(){
        JSONArray jsonArray = new JSONArray();
        List<Map<String, Object>> kh = service.getKH();
        jsonArray.addAll(kh);
        return jsonArray.toJSONString();
    }
}
