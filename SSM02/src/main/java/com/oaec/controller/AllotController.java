package com.oaec.controller;

import com.alibaba.fastjson.JSON;
import com.oaec.service.AllotService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//客户分配

@Controller
@RequestMapping("/allot")
public class AllotController {
    @Resource
    private AllotService service;

    //跳转主页
    @RequestMapping("/toMain")
    public String toMain(){
        return "allot";
    }

    //查询所有的客户分配
    @RequestMapping(value="/getAllAllot",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getAllAllot(int page,
                              int rows){

        Map<String,Object> param = new HashMap<String, Object>();
        param.put("pageNo",page);
        param.put("pageSize",rows);

        Map<String, Object> allStatus = service.findAllAllot(param);

        return JSON.toJSONString(allStatus);
    }

}
