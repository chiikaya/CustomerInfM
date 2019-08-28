package com.oaec.serviceImpl;

import com.oaec.dao.TypeMapper;
import com.oaec.service.TypeService;
import com.oaec.util.ModuleUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TypeServiceImpl implements TypeService {
    @Resource
    private ModuleUtil util;

    @Resource
    private TypeMapper mapper;

    //查询数据  1+2
    @Override
    public Map<String, Object> findAllType(Map<String, Object> map) {
        //页码
        int pageNo =Integer.parseInt(map.get("pageNo").toString());
        int pageSize=Integer.parseInt(map.get("pageSize").toString());
        //起始下标
        int startIndex = (pageNo-1)*pageSize; // limit 0,10
        map.put("startIndex",startIndex);
        //总条数
        int totalCount = mapper.findTypeCount(map);
        List<Map<String, Object>> allRole = mapper.findAllType(map);
        //封装成对应的json格式
        Map<String,Object> result = new HashMap<>();
        result.put("total",totalCount);
        result.put("rows",allRole);

        return result;
    }


    // 添加或修改房屋类型 3+4
    @Override
    public int saveOrUpdateType(Map<String, Object> map) {
        Object type_id = map.get("type_id");
        int res=-1;
        if(type_id==null ||type_id==""){
            res= mapper.addType(map);
        }else{
            res= mapper.updateType(map);
        }
        return res;
    }

    // 假删除 5
    @Override
    public boolean deleteType(int type_id) {
        int i = mapper.deleteType(type_id);
        return i!=-1?true:false;
    }
}
