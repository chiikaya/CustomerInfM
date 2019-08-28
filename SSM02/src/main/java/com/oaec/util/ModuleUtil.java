package com.oaec.util;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ModuleUtil {

   // 把查询的原始数据组织成tree_data_json格式
    public List<Map<String,Object>> moduleUtil(List<Map<String,Object>> list){
        //准备接收重新组织的结果
        List<Map<String,Object>> result = new ArrayList<>();
        //准备盛放子节点
        List<Map<String,Object>> children = null;


        if(list!=null && list.size()>0){
            for(int i=0;i<list.size();i++){
                children = new ArrayList<>();
                //数据中的一行记录
                Map<String, Object> node1 = list.get(i);
                for(int j=0;j<list.size();j++){
                    Map<String, Object> node2 = list.get(j);
                    if(node1.get("id") == node2.get("parent_id")){
                        children.add(node2);
                    }
                }
                node1.put("children",children);
            }

            /*原来传过来的list，用户、角色、权限已经被添加成系统管理的子节点了,
            * 但是这个list中原来的用户、角色、权限我们还没有删掉*/
            for(Map<String,Object> map:list){
                if(map.get("auth_layer").toString().equals("1")){
                    result.add(map);
                }
            }
        }

        return result;
    }
    public static List<Map<String,Object>> getcombobox(List<Map<String,Object>> list){
        List<Map<String,Object>> result = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("id",list.get(i).get("id"));
            map.put("text",list.get(i).get("name"));

            result.add(map);
        }
        return result;
    }
}
