package com.oaec.serviceImpl;


import com.oaec.dao.FromMapper;
import com.oaec.service.FromService;
import com.oaec.util.ModuleUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FromServiceImpl implements FromService {
    @Resource
    private ModuleUtil util;

    @Resource
    private FromMapper mapper;

    //查询数据  1+2
    @Override
    public Map<String, Object> findAllFrom(Map<String, Object> map) {
        //页码
        int pageNo =Integer.parseInt(map.get("pageNo").toString());
        int pageSize=Integer.parseInt(map.get("pageSize").toString());
        //起始下标
        int startIndex = (pageNo-1)*pageSize; // limit 0,10
        map.put("startIndex",startIndex);
        //总条数
        int totalCount = mapper.findFromCount(map);
        List<Map<String, Object>> allRole = mapper.findAllFrom(map);
        //封装成对应的json格式
        Map<String,Object> result = new HashMap<>();
        result.put("total",totalCount);
        result.put("rows",allRole);

        return result;
    }

    // 添加或修改客户来源   // 3+4
    @Override
    public int saveOrUpdateFrom(Map<String, Object> map) {
        Object from_id = map.get("from_id");
        int res=-1;
        if(from_id==null ||from_id==""){
            res= mapper.addFrom(map);
        }else{
            res= mapper.updateFrom(map);
        }
        return res;
    }

    // 假删除 5
    @Override
    public boolean deleteFrom(int from_id) {
        int i = mapper.deleteFrom(from_id);
        return i!=-1?true:false;
    }

}
