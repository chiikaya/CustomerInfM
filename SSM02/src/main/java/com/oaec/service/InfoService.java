package com.oaec.service;

import java.util.List;
import java.util.Map;

public interface InfoService {
    //查询数据
    Map<String,Object> findAllInfo(Map<String, Object> map);  // 1+2

    // 添加或修改房屋信息
    int saveOrUpdateInfo(Map<String,Object> map);   // 3+4

    // 假删除
    boolean deleteInfo(int info_id); //5


    //户型
    public List<Map<String,Object>> getType();//6

    //管理员
    public List<Map<String,Object>> getDesc2();//7
}
