package com.oaec.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.oaec.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//公告
@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Resource
    private NoticeService service;

    //跳转主页
    @RequestMapping("/toMain")
    public String toMain(){
        return "notice";
    }

    //1.查询所有的公告
    @RequestMapping(value = "/getAllNotice",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getAllNotice(String notice_text,
                                String notice_way,
                                int page,
                                int rows){

        Map<String,Object> param = new HashMap<>();
        param.put("notice_text",notice_text);
        param.put("notice_way",notice_way);
        param.put("pageNo",page);
        param.put("pageSize",rows);

        Map<String, Object> allThought = service.findAllNotice(param);

        return JSON.toJSONString(allThought);
    }

    // notice_id,notice_person,notice_text,notice_content,create_time,reply_time
    //2. 添加或修改
    @RequestMapping(value = "/saveOrUpdateNotice",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String saveOrUpdateNotice(String notice_id,
                                      String notice_person,
                                      String notice_text,
                                      String notice_content,
                                      String create_time3,
                                      String reply_time3){

        Map<String,Object> map = new HashMap<>();
        map.put("notice_id",notice_id);
        map.put("notice_person",notice_person);
        map.put("notice_text",notice_text);
        map.put("notice_content",notice_content);
        map.put("create_time",create_time3);
        map.put("reply_time",reply_time3);
        int i = service.saveOrUpdateNotice(map);
        String res = i==1?"success":"error";
        JSONObject obj = new JSONObject();
        obj.put("info",res);
        return obj.toJSONString();
    }


    //3. 删除
    @RequestMapping(value="/deleteNotice",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deleteNotice(int notice_id){
        boolean b = service.deleteNotice(notice_id);
        JSONObject object=new JSONObject();
        object.put("info",b?"success":"error");
        return object.toJSONString();
    }





    //*   ---------------获取下拉列表---------------------------*/
    //员工
    @RequestMapping(value = "/getDESC",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getDESC(){
        JSONArray jsonArray = new JSONArray();
        List<Map<String, Object>> yuangong = service.getDESC();
        jsonArray.addAll(yuangong);
        return jsonArray.toJSONString();
    }
}