package com.oaec.dao;

import java.util.List;
import java.util.Map;

//权限
public interface AuthMapper {

    //1. 查询所有权限
    List<Map<String,Object>> getAllAuth();

    //2. 添加权限
    int addAuth(Map<String,Object> map);

    //3. 修改权限
    int updateAuth(Map<String,Object> map);
}
