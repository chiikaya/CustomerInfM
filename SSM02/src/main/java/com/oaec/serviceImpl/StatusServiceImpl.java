package com.oaec.serviceImpl;

import com.oaec.dao.StatusMapper;
import com.oaec.service.StatusService;
import com.oaec.util.ModuleUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatusServiceImpl implements StatusService {

    @Resource
    private ModuleUtil util;

    @Resource
    private StatusMapper mapper;


    //查询数据  1+2
    @Override
    public Map<String, Object> findAllStatus(Map<String, Object> map) {
        //页码
        int pageNo =Integer.parseInt(map.get("pageNo").toString());
        int pageSize=Integer.parseInt(map.get("pageSize").toString());
        //起始下标
        int startIndex = (pageNo-1)*pageSize; // limit 0,10
        map.put("startIndex",startIndex);
        //总条数
        int totalCount = mapper.findStatusCount(map);
        List<Map<String, Object>> allRole = mapper.findAllStatus(map);
        //封装成对应的json格式
        Map<String,Object> result = new HashMap<>();
        result.put("total",totalCount);
        result.put("rows",allRole);

        return result;
    }

    // 添加或修改客户状态    // 3+4
    @Override
    public int saveOrUpdateStatus(Map<String, Object> map) {
        Object status_id = map.get("status_id");
        int res=-1;
        if(status_id==null ||status_id==""){
            res= mapper.addStatus(map);
        }else{
            res= mapper.updateStatus(map);
        }
        return res;
    }

    // 假删除
    @Override
    public boolean deleteStatus(int status_id) {
        int i = mapper.deleteStatus(status_id);
        return i!=-1?true:false;
    }
}
