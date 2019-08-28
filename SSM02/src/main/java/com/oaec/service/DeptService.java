package com.oaec.service;

import java.util.Map;

public interface DeptService {

    //查询数据
    Map<String,Object> findAllDept(Map<String, Object> map);  // 1+2

    // 添加或修改部门
    int saveOrUpdateDept(Map<String,Object> map);   // 3+4

    //删除部门
    int deleteDept(int dept_id);   // 5+6+7
}
