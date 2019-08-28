package com.oaec.service;

import java.util.List;
import java.util.Map;

public interface StatusService {

    //查询数据
    Map<String,Object> findAllStatus(Map<String, Object> map);  // 1+2

    // 添加或修改客户状态
    int saveOrUpdateStatus(Map<String,Object> map);   // 3+4

    // 假删除
    boolean deleteStatus(int status_id); //5
}
