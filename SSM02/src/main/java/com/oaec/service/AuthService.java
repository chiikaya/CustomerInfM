package com.oaec.service;

import java.util.List;
import java.util.Map;

public interface AuthService {

    //1.查询所有的权限
    List<Map<String,Object>>  getAllAuth();

    //2. 添加或修改权限
    int saveOrUpdateAuth(Map<String,Object> map);
}
