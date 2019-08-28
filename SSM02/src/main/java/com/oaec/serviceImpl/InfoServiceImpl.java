package com.oaec.serviceImpl;

import com.oaec.dao.InfoMapper;
import com.oaec.service.InfoService;
import com.oaec.util.ModuleUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InfoServiceImpl implements InfoService {
    @Resource
    private ModuleUtil util;

    @Resource
    private InfoMapper mapper;

    //查询数据   1+2
    @Override
    public Map<String, Object> findAllInfo(Map<String, Object> map) {
        String info_text = (String)map.get("info_text");
        String info_where = (String)map.get("info_where");

        if (info_text != null && info_where != null) {
            if (("房屋类型").equals(info_where)) {
                map.put("byInfoType", info_text);
            }
            if (("房屋地址").equals(info_where)) {
                map.put("byInfoAdress", info_text);
            }

        }
        //页码
        int pageNo =Integer.parseInt(map.get("pageNo").toString());
        int pageSize=Integer.parseInt(map.get("pageSize").toString());
        //起始下标
        int startIndex = (pageNo-1)*pageSize; // limit 0,10
        map.put("startIndex",startIndex);
        //总条数
        int totalCount = mapper.findInfoCount(map);
        List<Map<String, Object>> allInfo = mapper.findAllInfo(map);
        //封装成对应的json格式
        Map<String,Object> result = new HashMap<>();
        result.put("total",totalCount);
        result.put("rows",allInfo);

        return result;
    }

    // 添加或修改房屋信息  3+4
    @Override
    public int saveOrUpdateInfo(Map<String, Object> map) {
        Object info_id = map.get("info_id");
        int res=-1;
        if(info_id==null ||info_id==""){
            res= mapper.addInfo(map);
        }else{
            res= mapper.updateInfo(map);
        }
        return res;
    }

    // 假删除  5
    @Override
    public boolean deleteInfo(int info_id) {
        int i = mapper.deleteInfo(info_id);
        return i!=-1?true:false;
    }

    //户型
    @Override
    public List<Map<String, Object>> getType() {
        List<Map<String, Object>> hx = mapper.getType();
        List<Map<String, Object>> list = util.getcombobox(hx);
        return list;
    }

    //管理员
    @Override
    public List<Map<String, Object>> getDesc2() {
        List<Map<String, Object>>gy  = mapper.getDesc2();
        List<Map<String, Object>> list = util.getcombobox(gy);
        return list;
    }
}
