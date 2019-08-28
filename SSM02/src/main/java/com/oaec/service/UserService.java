package com.oaec.service;

import java.util.List;
import java.util.Map;

public interface UserService {

    //查询数据
    Map<String,Object> findAllUser(Map<String,Object> map);  // 1+2

    // 添加或修改角色
    int saveOrUpdateUser(Map<String,Object> map);   // 3+4

    //根据用户id查询角色
    List<Map<String,Object>> getRoleByUserId(int user_id);   // 5+6

    //保存授权
    boolean saveRoleOfUser(int user_id,int [] arr);  //7+8

    //删除用户
    boolean deleteUser(int user_id); //9

    /*保存部门*/
    boolean saveDptOfUser(Map<String,Integer> map); //10

    /*根据角色id查询部门*/
    List<Map<String, Object>> getDptByUserId(int user_id);//11+12

    //13.部门
    public List<Map<String,Object>> getUSDept();
}
