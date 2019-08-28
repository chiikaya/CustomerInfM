package com.oaec.service;

import java.util.List;
import java.util.Map;

public interface RecordService {

    //查询数据
    Map<String,Object> findAllRecord(Map<String,Object> map);  // 1+2

    // 添加或修改联系记录
    int saveOrUpdateRecord(Map<String,Object> map);   // 3+4

    //删除
    boolean deleteRecord(int record_id);    //5




   /*       下拉列表          */
    //客户
    public List<Map<String,Object>> getKH();

    //联系人姓名
    public List<Map<String,Object>> getPerson();
}

