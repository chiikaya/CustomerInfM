package com.oaec.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.oaec.service.RecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 联系记录
@Controller
@RequestMapping("/record")
public class RecordController {

        @Resource
        private RecordService service;

        //跳转主页
        @RequestMapping("/toMain")
        public String toMain() {
            return "record";
        }

        //1.查询所有的联系记录
        @RequestMapping(value = "/getAllRecord", produces = "text/html;charset=utf-8")
        @ResponseBody
        public String getAllRecord(String record_text,
                                   String record_way,
                                   int page,
                                   int rows) {

            Map<String, Object> param = new HashMap<>();
            param.put("record_text", record_text);
            param.put("record_way", record_way);
            param.put("pageNo", page);
            param.put("pageSize", rows);

            Map<String, Object> allThought = service.findAllRecord(param);

            return JSON.toJSONString(allThought);
        }

    //2. 添加或修改联系记录
       /* record_id,record_name,create_time,reply_time, record_type,record_who,record_text,record_desc*/
    @RequestMapping(value = "/saveOrUpdateRecord",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String saveOrUpdateRecord(String record_id,
                                     String client_id,
                                      String create_time,
                                      String reply_time,
                                      String record_type,
                                      String record_who,
                                      String record_text,
                                      String record_desc){
        Map<String,Object> map = new HashMap<>();
        map.put("record_id",record_id);
        map.put("client_id",client_id);
        map.put("create_time",create_time);
        map.put("reply_time",reply_time);
        map.put("record_type",record_type);
        map.put("record_who",record_who);
        map.put("record_text",record_text);
        map.put("record_desc",record_desc);
        System.out.println(">>>>>>>>>>>>>>>>>>>>");
        System.out.println(map);

        int i = service.saveOrUpdateRecord(map);
        String res = i==1?"success":"error";
        JSONObject obj = new JSONObject();
        obj.put("info",res);
        return obj.toJSONString();
    }


    //3. 删除客户
    @RequestMapping(value="/deleteRecord",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deleteRecord(int record_id){
        boolean b = service.deleteRecord(record_id);
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
    //联系人
    @RequestMapping(value = "/getPerson",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getPerson(){
        JSONArray jsonArray = new JSONArray();
        List<Map<String, Object>> person = service.getPerson();
        jsonArray.addAll(person);
        return jsonArray.toJSONString();
    }
    }
