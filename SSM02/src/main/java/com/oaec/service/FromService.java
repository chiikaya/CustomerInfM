package com.oaec.service;

import java.util.Map;

public interface FromService {

    //查询数据
    Map<String,Object> findAllFrom(Map<String, Object> map);  // 1+2

    // 添加或修改客户来源
    int saveOrUpdateFrom(Map<String,Object> map);   // 3+4

    // 假删除
   boolean deleteFrom(int from_id); //5

}
