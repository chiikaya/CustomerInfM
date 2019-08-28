package com.oaec.service;

import java.util.List;
import java.util.Map;

public interface DescService {
    //查询数据
    Map<String,Object> findAllDesc(Map<String, Object> map);  // 1+2

    // 添加或修改员工信息
    int saveOrUpdateDesc(Map<String,Object> map);   // 3+4

    // 假删除员工
    boolean deleteDesc(int staff_id); //5




    //部门
    public List<Map<String,Object>> getDept();//6

    //角色
    public List<Map<String,Object>> getRole(); //7

}
