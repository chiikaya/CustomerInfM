package com.oaec.service;

import java.util.List;
import java.util.Map;

public interface RoleService {

    //查询数据
    Map<String,Object> findAllRole(Map<String,Object> map);  // 1+2

    // 添加或修改角色
    int saveOrUpdateRole(Map<String,Object> map);   // 3+4

    //根据角色id查询权限
    List<Map<String,Object>> getAuthByRoleId(int role_id);   // 5+6

    //保存授权
    boolean saveAuthOfRole(int role_id,int [] arr);  //7+8

    //删除角色
    boolean deleteRole(int role_id);    //9
}
