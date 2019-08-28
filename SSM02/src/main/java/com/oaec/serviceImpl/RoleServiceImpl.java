package com.oaec.serviceImpl;

import com.oaec.dao.RoleMapper;
import com.oaec.service.RoleService;
import com.oaec.util.ModuleUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private ModuleUtil util;

    @Resource
    private RoleMapper mapper;

    //查询数据  1+2
    @Override
    public Map<String, Object> findAllRole(Map<String, Object> map) {
        //页码
        int pageNo =Integer.parseInt(map.get("pageNo").toString());
        int pageSize=Integer.parseInt(map.get("pageSize").toString());
        //起始下标
        int startIndex = (pageNo-1)*pageSize; // limit 0,10
        map.put("startIndex",startIndex);
        //总条数
        int totalCount = mapper.findRoleCount(map);
        List<Map<String, Object>> allRole = mapper.findAllRole(map);
        //封装成对应的json格式
        Map<String,Object> result = new HashMap<>();
        result.put("total",totalCount);
        result.put("rows",allRole);

        return result;
    }

    // 添加或修改角色    // 3+4
    @Override
    public int saveOrUpdateRole(Map<String, Object> map) {
        Object role_id = map.get("role_id");
        int res=-1;
        if(role_id==null ||role_id==""){
            res= mapper.addRole(map);
        }else{
            res= mapper.updateRole(map);
        }
        return res;
    }

    //根据角色id查询权限   // 5+6
    @Override
    public List<Map<String, Object>> getAuthByRoleId(int role_id) {
        List<Map<String, Object>> allAuth = mapper.getAllInvalidAuth();
        List<Integer> authByRoleId = mapper.getInvalidAuthByRoleId(role_id);

        //给权限添加checked 属性
        for(int i=0;i<allAuth.size();i++){
            Map<String, Object> node = allAuth.get(i);
            for(int j=0;j<authByRoleId.size();j++){
                if(node.get("id")==authByRoleId.get(j)){
                    node.put("checked",true);
                }
            }
        }
        //格式化为tree所需要的格式
        List<Map<String, Object>> list = util.moduleUtil(allAuth);
        return list;
    }

    //保存授权     //7+8
    @Transactional  //事务
    @Override
    public boolean saveAuthOfRole(int role_id, int[] arr) {
        //删除当前角色之前的全部权限
        int res1 = mapper.deleteAllAuthOfRole(role_id);

        List<Map<String,Object>> list=new ArrayList<>();
        Map<String,Object> map=null;
        int res2=-100;
        if(arr!=null && arr.length>0){
            for(int i=0;i<arr.length;i++){
                map=new HashMap<>();
                map.put("role_id",role_id);
                map.put("auth_id",arr[i]);
                list.add(map);
            }
            //插入最新的权限
            res2 = mapper.addAuthOfRole(list);
        }
        return res1>=0 && (res2==-100||res2==arr.length) ? true : false;
    }

    //删除角色      //9
    @Transactional  //事务
    @Override
    public boolean deleteRole(int role_id) {
        //删除当前角色之前的全部权限
        int res1 = mapper.deleteAllAuthOfRole(role_id);
        int res2 = mapper.deleteRole(role_id);
        if(res1>=0 && res2>=0){
            return true;
        }else{
            return false;
        }
    }
}
