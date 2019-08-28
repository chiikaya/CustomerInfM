package com.oaec.serviceImpl;

import com.oaec.dao.AllotMapper;
import com.oaec.service.AllotService;
import com.oaec.util.ModuleUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AllotServiceImpl implements AllotService {
    @Resource
    private ModuleUtil util;

    @Resource
    private AllotMapper mapper;

    //查询数据  1+2
    @Override
    public Map<String, Object> findAllAllot(Map<String, Object> map) {
        //页码
        int pageNo =Integer.parseInt(map.get("pageNo").toString());
        int pageSize=Integer.parseInt(map.get("pageSize").toString());
        //起始下标
        int startIndex = (pageNo-1)*pageSize; // limit 0,10
        map.put("startIndex",startIndex);
        //总条数
        int totalCount = mapper.findAllotCount(map);
        List<Map<String, Object>> allRole = mapper.findAllAllot(map);


        //格式化日期
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        for(Map<String, Object> map1:allRole){
            Object create_time = map1.get("create_time");

            Date d =null;
            if(create_time instanceof  Date){
                d = (Date)create_time;
            }

            String format = sdf1.format(d);
            map1.put("create_time",format);

        }



        //封装成对应的json格式
        Map<String,Object> result = new HashMap<String, Object>();
        result.put("total",totalCount);
        result.put("rows",allRole);

        return result;
    }
}
