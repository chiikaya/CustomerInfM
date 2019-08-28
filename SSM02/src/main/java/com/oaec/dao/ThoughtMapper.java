package com.oaec.dao;

import java.util.List;
import java.util.Map;

public interface ThoughtMapper {
    //1. 查询总条数
    int findThoughtCount(Map<String,Object> map);

    //2. 查询数据
    List<Map<String,Object>> findAllThought(Map<String,Object> map);

    //3. 添加
    int addThought(Map<String, Object> map);

    //4. 修改
    int updateThought(Map<String, Object> map);

    //5. 删除客户
    int deleteThought(int thought_id);


      /*               下拉列表                          */
    //客户
    public List<Map<String,Object>> getKH();
}
