package com.oaec.dao;


import java.util.List;
import java.util.Map;

//开始界面
public interface SysMapper {

   //1. 用户登录
   List<Map<String,Object>> userLogin(Map<String,Object> map) ;

   //2.根据用户id查询用户所有的菜单权限
   List<Map<String,Object>> getAllAuthByUserId(int user_id);

    //3.根据用户id查询所有的资源权限
    List<Map<String,Object>> getAllResourceByUserId(int user_id);

}
