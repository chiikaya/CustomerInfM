package com.oaec.service;

import java.util.Map;

public interface TypeService {
    //查询数据
    Map<String,Object> findAllType(Map<String, Object> map);  // 1+2

    // 添加或修改房屋类型
    int saveOrUpdateType(Map<String,Object> map);   // 3+4

    // 假删除
    boolean deleteType(int type_id); //5
}
