package com.oaec.serviceImpl;

import com.oaec.dao.RecordMapper;
import com.oaec.service.RecordService;
import com.oaec.util.ModuleUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecordServiceImpl implements RecordService{

    @Resource
    private ModuleUtil util;

    @Resource
    private RecordMapper mapper;

    //查询数据  1+2
    @Override
    public Map<String, Object> findAllRecord(Map<String, Object> map)  {

        String record_text = (String)map.get("record_text");
        String record_way = (String)map.get("record_way");

        if (record_text != null && record_way != null) {
            if (("客户姓名").equals(record_way)) {
                map.put("byREName", record_text);
            }
            if (("联系类型").equals(record_way)) {
                map.put("byREType", record_text);
            }
            if (("联系主题").equals(record_way)) {
                map.put("byREText", record_text);
            }

        }
        //页码
        int pageNo =Integer.parseInt(map.get("pageNo").toString());
        int pageSize=Integer.parseInt(map.get("pageSize").toString());
        //起始下标
        int startIndex = (pageNo-1)*pageSize; // limit 0,10
        map.put("startIndex",startIndex);
        //总条数
        int totalCount = mapper.findRecordCount(map);
        List<Map<String, Object>> allRole = mapper.findAllRecord(map);


        //格式化日期
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
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

    // 添加或修改联系记录   (3+4)
    @Override
    public int saveOrUpdateRecord(Map<String, Object> map) {
        Object record_id = map.get("record_id");
        int res=-1;
        if(record_id==null ||record_id==""){
            res= mapper.addRecord(map);
        }else{
            res= mapper.updateRecord(map);
        }
        return res;
    }



    //删除客户   5
    @Override
    public boolean deleteRecord(int record_id) {
        int res = mapper.deleteRecord(record_id);
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

    //联系人姓名
    @Override
    public List<Map<String, Object>> getPerson() {
        List<Map<String, Object>> person = mapper.getPerson();
        List<Map<String, Object>> list = util.getcombobox(person);
        return list;
    }
}
