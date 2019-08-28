package com.oaec.dao;

import java.util.List;
import java.util.Map;

//员工
public interface DescMapper {
    //1. 查询总条数
    int findDescCount(Map<String, Object> map);

    //2. 查询数据
    List<Map<String,Object>> findAllDesc(Map<String, Object> map);

    //3. 添加员工信息
    int addDesc(Map<String,Object> map);

    //4. 修改员工信息
    int updateDesc(Map<String,Object> map);

    //5. 假删除员工
    int deleteDesc(int staff_id);




    /*  下拉列表    */

    //6.部门
    public List<Map<String,Object>> getDept();

    //7.客户
    public List<Map<String,Object>> getRole();

}
