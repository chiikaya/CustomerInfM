package com.oaec.dao;

import java.util.List;
import java.util.Map;

public interface PersonMapper {
    //1. 查询总条数
    int findPersonCount(Map<String,Object> map);

    //2. 查询数据
    List<Map<String,Object>> findAllPerson(Map<String,Object> map);

    //3. 添加
    int addPerson(Map<String, Object> map);

    //4. 修改
    int updatePerson(Map<String, Object> map);

    //5. 删除
    int deletePerson(int person_id);



    //6. 客户
    public List<Map<String,Object>> getPersonKH();
}
