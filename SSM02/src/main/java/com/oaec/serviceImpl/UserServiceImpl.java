package com.oaec.serviceImpl;

import com.oaec.dao.UserMapper;
import com.oaec.service.UserService;
import com.oaec.util.ModuleUtil;
import com.oaec.util.ModuleUtil2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private ModuleUtil util;
    @Resource
    private ModuleUtil2 util2;
    @Resource
    private UserMapper mapper;



   //查询数据(1+2)
    @Override
    public Map<String, Object> findAllUser(Map<String, Object> map) {
        //页码
        int pageNo =Integer.parseInt(map.get("pageNo").toString());
        int pageSize=Integer.parseInt(map.get("pageSize").toString());
        //起始下标
        int startIndex = (pageNo-1)*pageSize; // limit 0,10
        map.put("startIndex",startIndex);
        //总条数
        int totalCount = mapper.findUserCount(map);
        List<Map<String, Object>> allUser = mapper.findAllUser(map);
        //封装成对应的json格式
        Map<String,Object> result = new HashMap<>();
        result.put("total",totalCount);
        result.put("rows",allUser);

        return result;
    }


    // 添加或修改用户(3+4)
    @Override
    public int saveOrUpdateUser(Map<String, Object> map) {
        Object role_id = map.get("user_id");
        int res=-1;
        if(role_id==null ||role_id==""){
            res= mapper.addUser(map);
        }else{
            res= mapper.updateUser(map);
        }
        return res;
    }

    //根据用户id查询角色(5+6)
    @Override
    public List<Map<String, Object>> getRoleByUserId(int user_id) {
        List<Map<String, Object>> allRole = mapper.getAllInvalidRole();
        List<Integer> roleByUserId = mapper.getInvalidRoleByUserId(user_id);

        //给用户添加checked 属性
        for(int i=0;i<allRole.size();i++){
            System.out.println("---");
            Map<String, Object> node2 = allRole.get(i);
            for(int j=0;j<roleByUserId.size();j++){
                if(node2.get("id")==roleByUserId.get(j)){
                  node2.put("checked",true);
                }
            }
        }

        //格式化为tree所需要的格式
     /*   List<Map<String, Object>> list = util.moduleUtil(allRole);*/

       /* System.out.println("list================="+list);*/
        return allRole;
    }

    //保存授权(7+8)
    @Override
    public boolean saveRoleOfUser(int user_id, int[] arr) {
        //删除当前角色之前的全部权限
        int res1 = mapper.deleteAllRoleOfUser(user_id);

        List<Map<String,Object>> list=new ArrayList<>();
        Map<String,Object> map=null;
        int res2=-100;
        if(arr!=null && arr.length>0){
            for(int i=0;i<arr.length;i++){
                map=new HashMap<>();
                map.put("user_id",user_id);
                map.put("role_id",arr[i]);
                list.add(map);
            }
            //插入最新的权限
            res2 = mapper.addRoleOfUser(list);
        }
        return res1>=0 && (res2==-100||res2==arr.length) ? true : false;
    }

    //删除用户 9
    @Override
    public boolean deleteUser(int user_id) {
        int i = mapper.deleteUser(user_id);
        return i == 1 ? true : false;
    }

    // 保存部门 10
    @Override
    public boolean saveDptOfUser(Map<String, Integer> map) {

        int i = mapper.saveDptOfUser(map);

        return i == 1 ? true : false;
    }

    //根据角色id查询部门 11+12
    @Override
    public List<Map<String, Object>> getDptByUserId(int user_id) {

        List<Map<String, Object>> allDpt = mapper.getAllInvalidDpt();
        System.out.println("所有的部门:" + allDpt);
        List<Map<String, Integer>> dptByUserId = mapper.getInvalidDptByUserId(user_id);
        System.out.println("当前用户的部门id：" + dptByUserId);
        List<Map<String, Object>> list = util2.moduleUtil(allDpt, dptByUserId);
        return list;
    }

    //13.部门
    @Override
    public List<Map<String, Object>> getUSDept() {
        List<Map<String, Object>> bu = mapper.getUSDept();
        List<Map<String, Object>> list = util.getcombobox(bu);
        return list;
    }

}
