package com.oaec.serviceImpl;

import com.oaec.dao.MoldMapper;
import com.oaec.service.MoldService;
import com.oaec.util.ModuleUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MoldServiceImpl implements MoldService {
    @Resource
    private ModuleUtil util;

    @Resource
    private MoldMapper mapper;

    //查询数据  1+2
    @Override
    public Map<String, Object> findAllMold(Map<String, Object> map) {
        //页码
        int pageNo =Integer.parseInt(map.get("pageNo").toString());
        int pageSize=Integer.parseInt(map.get("pageSize").toString());
        //起始下标
        int startIndex = (pageNo-1)*pageSize; // limit 0,10
        map.put("startIndex",startIndex);
        //总条数
        int totalCount = mapper.findMoldCount(map);
        List<Map<String, Object>> allRole = mapper.findAllMold(map);
        //封装成对应的json格式
        Map<String,Object> result = new HashMap<>();
        result.put("total",totalCount);
        result.put("rows",allRole);

        return result;
    }

    // 添加或修改客户类型  // 3+4
    @Override
    public int saveOrUpdateMold(Map<String, Object> map) {
        Object mold_id = map.get("mold_id");
        int res=-1;
        if(mold_id==null ||mold_id==""){
            res= mapper.addMold(map);
        }else{
            res= mapper.updateMold(map);
        }
        return res;
    }

    // 假删除
    @Override
    public boolean deleteMold(int mold_id) {
        int i = mapper.deleteMold(mold_id);
        return i!=-1?true:false;
    }
}
