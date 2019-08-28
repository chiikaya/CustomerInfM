package com.oaec.serviceImpl;

import com.oaec.dao.ThoughtMapper;
import com.oaec.service.ThoughtService;
import com.oaec.util.ModuleUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ThoughtServiceImpl implements ThoughtService {

    @Resource
    private ModuleUtil util;

    @Resource
    private ThoughtMapper mapper;

    //查询数据  1+2
    @Override
    public Map<String, Object> findAllThought(Map<String, Object> map)  {
        String thought_text = (String)map.get("thought_text");
        String thought_way = (String)map.get("thought_way");

        if (thought_text != null && thought_way != null) {
            if (("客户").equals(thought_way)) {
                map.put("byKH", thought_text);
            }
            if (("关怀主题").equals(thought_way)) {
                map.put("byText", thought_text);
            }
            if (("关怀方式").equals(thought_way)) {
                map.put("byWay", thought_text);
            }

        }
        //页码
        int pageNo =Integer.parseInt(map.get("pageNo").toString());
        int pageSize=Integer.parseInt(map.get("pageSize").toString());
        //起始下标
        int startIndex = (pageNo-1)*pageSize; // limit 0,10
        map.put("startIndex",startIndex);
        //总条数
        int totalCount = mapper.findThoughtCount(map);
        List<Map<String, Object>> allRole = mapper.findAllThought(map);


        //格式化日期
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
       // SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
        for(Map<String, Object> map1:allRole){
            Object create_time = map1.get("create_time");
            Object reply_time = map1.get("reply_time");


            Date d =null;
            Date d2 =null;
            if(create_time instanceof  Date){
                d = (Date)create_time;
            }
            if(reply_time instanceof Date){
                d2 = (Date)reply_time;
            }

            String format = sdf1.format(d);
            String format2 = sdf1.format(d2);
            map1.put("create_time",format);
            map1.put("reply_time",format2);
        }

        //封装成对应的json格式
        Map<String,Object> result = new HashMap<>();
        result.put("total",totalCount);
        result.put("rows",allRole);

        return result;
    }

    // 添加或修改客户关怀   (3+4)
    @Override
    public int saveOrUpdateThought(Map<String, Object> map) {
        Object thought_id = map.get("thought_id");
        int res=-1;
        if(thought_id==null ||thought_id==""){
            res= mapper.addThought(map);
        }else{
            res= mapper.updateThought(map);
        }
        return res;
    }



    //删除客户   5
    @Override
    public boolean deleteThought(int thought_id) {
        int res = mapper.deleteThought(thought_id);
        return res == 1 ? true : false;
    }




/*下拉列表*/
    //客户
    @Override
    public List<Map<String, Object>> getKH() {
        List<Map<String, Object>> kh = mapper.getKH();
        List<Map<String, Object>> list = util.getcombobox(kh);
        return list;
    }

}
