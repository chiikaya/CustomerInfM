package com.oaec.service;

import java.util.List;
import java.util.Map;

public interface ThoughtService {
    //查询数据
    Map<String,Object> findAllThought(Map<String,Object> map);  // 1+2

    // 添加或修改客户关怀
    int saveOrUpdateThought(Map<String,Object> map);   // 3+4

    //删除客户
    boolean deleteThought(int thought_id);    //5




/*                        下拉列表           */
    //客户
    public List<Map<String,Object>> getKH();
}
