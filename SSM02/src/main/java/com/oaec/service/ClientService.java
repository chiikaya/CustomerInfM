package com.oaec.service;

import java.util.List;
import java.util.Map;

//客户
public interface ClientService {

    //查询数据
    Map<String,Object> findAllClient(Map<String, Object> map);  // 1+2

    // 添加或修改客户信息
    int saveOrUpdateClient(Map<String,Object> map);   // 3+4

    // 假删除客户
    boolean deleteClient(int client_id); //5






    //客户状态
    public List<Map<String,Object>> getStatus(); //5

    //客户来源
    public List<Map<String,Object>> getFrom(); //6

    //所属员工
    public List<Map<String,Object>> getStaff(); //7

    //客户类型
    public List<Map<String,Object>> getMold();//8


}
