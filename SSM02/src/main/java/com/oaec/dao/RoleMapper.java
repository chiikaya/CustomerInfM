package com.oaec.dao;

import java.util.List;
import java.util.Map;

public interface RoleMapper {

    //1. 查询总条数
    int findRoleCount(Map<String,Object> map);

    //2. 查询数据
    List<Map<String,Object>> findAllRole(Map<String,Object> map);

    //3. 添加角色
    int addRole(Map<String,Object> map);

    //4. 修改角色
    int updateRole(Map<String,Object> map);

    //5. 查询所有的权限
    List<Map<String,Object>> getAllInvalidAuth();

    //6. 按角色ID查询所有有效的权限  sys_role_auth
    List<Integer> getInvalidAuthByRoleId(int role_id);

    //7. 删除当前角色的所有权限
    int deleteAllAuthOfRole(int role_id);

    //8. 给角色重新授权
    int addAuthOfRole(List<Map<String,Object>> list);

    //9. 删除角色
    int deleteRole(int role_id);

}
