package com.oaec.service;

import java.util.Map;

public interface MoldService {

    //查询数据
    Map<String,Object> findAllMold(Map<String, Object> map);  // 1+2

    // 添加或修改客户类型
    int saveOrUpdateMold(Map<String,Object> map);   // 3+4

    // 假删除
    boolean deleteMold(int mold_id); //5

}
