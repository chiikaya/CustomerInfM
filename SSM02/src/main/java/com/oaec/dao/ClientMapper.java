package com.oaec.dao;

import java.util.List;
import java.util.Map;

//客户
public interface ClientMapper {
    //1. 查询总条数
    int findClientCount(Map<String, Object> map);

    //2. 查询数据
    List<Map<String,Object>> findAllClient(Map<String, Object> map);

    //3. 添加客户信息
    int addClient(Map<String,Object> map);

    //4. 修改客户信息
    int updateClient(Map<String,Object> map);


    //5. 假删除客户
    int deleteClient(int client_id);









    /*  下拉列表    */

    //5.客户状态
    public List<Map<String,Object>> getStatus();

    //6.客户来源
    public List<Map<String,Object>> getFrom();

    //7.所属员工
    public List<Map<String,Object>> getStaff();

    //8.客户类型
    public List<Map<String,Object>> getMold();

}
