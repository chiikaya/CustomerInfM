package com.oaec.service;

import java.util.List;
import java.util.Map;

public interface PersonService {

    //查询数据
    Map<String,Object> findAllPerson(Map<String, Object> map);  // 1+2

    // 添加或修改联系记录
    int saveOrUpdatePerson(Map<String, Object> map);   // 3+4

    //删除
    boolean deletePerson(int person_id);    //5


    /*               下拉列表                          */
    //客户
    public List<Map<String,Object>> getPersonKH();  //6

}
