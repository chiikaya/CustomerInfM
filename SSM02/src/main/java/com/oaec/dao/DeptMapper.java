package com.oaec.dao;

import java.util.List;
import java.util.Map;

public interface DeptMapper {

    //1. 查询总条数
    int findDeptCount(Map<String, Object> map);

    //2. 查询数据
    List<Map<String,Object>> findAllDept(Map<String, Object> map);

    //3. 添加部门
    int addDept(Map<String,Object> map);

    //4. 修改部门
    int updateDept(Map<String,Object> map);

    //5.假删部门
    int deleteDept(int map);

    //6.查询出被删除部门下的所有员工
    List<Integer> findUser4DeleteDept(int map);

    //7.修改被删除员工的部门
    int updateUserDept(List<Integer> list);



}
