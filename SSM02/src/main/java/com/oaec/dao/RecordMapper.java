package com.oaec.dao;

import java.util.List;
import java.util.Map;

public interface RecordMapper {

    //1. 查询总条数
    int findRecordCount(Map<String,Object> map);

    //2. 查询数据
    List<Map<String,Object>> findAllRecord(Map<String,Object> map);

    //3. 添加
    int addRecord(Map<String, Object> map);

    //4. 修改
    int updateRecord(Map<String, Object> map);

    //5. 删除
    int deleteRecord(int record_id);



    /*               下拉列表                          */
    //客户
    public List<Map<String,Object>> getKH();

    //联系人姓名
    public List<Map<String,Object>> getPerson();
}
