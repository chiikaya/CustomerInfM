package com.oaec.dao;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    //1. 查询总条数
    int findUserCount(Map<String, Object> map);

    //2. 查询数据
    List<Map<String,Object>> findAllUser(Map<String, Object> map);

    //3. 添加用户
    int addUser(Map<String, Object> map);

    //4. 修改用户
    int updateUser(Map<String, Object> map);

  //5. 查询所有有效角色
    List<Map<String,Object>> getAllInvalidRole();

    // 6.根据员工编号查询有效角色  sys_user_role
    List<Integer> getInvalidRoleByUserId(int user_id);

   //7. 删除当前用户的所有角色
    int deleteAllRoleOfUser(int role_id);

    //8. 给员工重新授权
    int addRoleOfUser(List<Map<String, Object>> list);

    //9. 删除用户
    int deleteUser(int user_id);


    /*10.保存部门*/
    int saveDptOfUser(Map<String,Integer> map);

    /*11.查询所有有效的部门*/
    List<Map<String,Object>> getAllInvalidDpt();


    /*12. 按角色ID查询所有有效的部门 */
    List<Map<String,Integer>> getInvalidDptByUserId(int user_id);

    //13.部门
    public List<Map<String,Object>> getUSDept();
}
