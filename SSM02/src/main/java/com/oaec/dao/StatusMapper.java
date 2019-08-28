package com.oaec.dao;

import java.util.List;
import java.util.Map;

public interface StatusMapper {
    //0
    public List<Map<String,Object>> queryAll(Map<String,Object> map);

    //1. 查询总条数
    int findStatusCount(Map<String, Object> map);

    //2. 查询数据
    List<Map<String,Object>> findAllStatus(Map<String, Object> map);

    //3. 添加客户状态
    int addStatus(Map<String,Object> map);

    //4. 修改客户状态
    int updateStatus(Map<String,Object> map);

    //5. 假删
    int deleteStatus(int status_id);

}
