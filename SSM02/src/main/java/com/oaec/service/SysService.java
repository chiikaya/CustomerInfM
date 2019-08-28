package com.oaec.service;

import java.util.List;
import java.util.Map;

public interface SysService {

    //1. 用户登录
    List<Map<String,Object>> userLogin(String username,String password);

    //2.根据用户id查询用户所有的权限
    List<Map<String,Object>> getAllAuthByUserId(int user_id);

    //3.根据用户id查询所有的资源权限
    List<Map<String,Object>> getAllResourceByUserId(int user_id);
}
