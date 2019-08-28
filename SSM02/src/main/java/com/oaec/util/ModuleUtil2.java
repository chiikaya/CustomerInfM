package com.oaec.util;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ModuleUtil2 {
    /*把查询的原始数据组织成tree_data_json格式*/

    /*
        角色格式
     */
    public List<Map<String, Object>> moduleUtil(List<Map<String, Object>> allRole, List<Map<String, Integer>> roleByUserId) {
        //准备接收重新组织的结果
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if (allRole != null && allRole.size() > 0) {
            for (int i = 0; i < allRole.size(); i++) {
                Map<String, Object> node = allRole.get(i);
                for (int j = 0; j < roleByUserId.size(); j++) {
                    Map<String, Integer> node2 = roleByUserId.get(j);
                    if (node2 != null && node2.size() > 0) {
                        if (node.get("id") == node2.get("id")) {
                            node.put("checked", true);
                        }
                    }
                }
                result.add(node);
            }
        }
        System.out.println(result);
        return result;
    }
}
