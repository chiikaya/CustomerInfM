package com.oaec.serviceImpl;

import com.oaec.dao.DescMapper;
import com.oaec.service.DescService;
import com.oaec.util.ModuleUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DescServiceImpl implements DescService {
    @Resource
    private ModuleUtil util;

    @Resource
    private DescMapper mapper;

    //查询数据  1+2
    @Override
    public Map<String, Object> findAllDesc(Map<String, Object> map) {

        String desc_text4 = (String)map.get("desc_text4");
        String desc_where4 = (String)map.get("desc_where4");

        if (desc_text4 != null && desc_where4 != null) {
            if (("员工姓名").equals(desc_where4)) {
                map.put("byDescName1", desc_text4);
            }
            if (("部门").equals(desc_where4)) {
                map.put("byDept1", desc_text4);
            }
            if (("角色").equals(desc_where4)) {
                map.put("byRole1", desc_text4);
            }
            if (("员工学历").equals(desc_where4)) {
                map.put("byDegree1", desc_text4);
            }

        }
        //页码
        int pageNo = Integer.parseInt(map.get("pageNo").toString());
        int pageSize = Integer.parseInt(map.get("pageSize").toString());
        //起始下标
        int startIndex = (pageNo - 1) * pageSize; // limit 0,10
        map.put("startIndex", startIndex);
        //总条数
        int totalCount = mapper.findDescCount(map);
        List<Map<String, Object>> allClient = mapper.findAllDesc(map);
        //封装成对应的json格式
        Map<String, Object> result = new HashMap<>();
        result.put("total", totalCount);
        result.put("rows", allClient);

        return result;
    }

    // 添加或修改员工信息 3+4
    @Override
    public int saveOrUpdateDesc(Map<String, Object> map) {
        Object staff_id = map.get("staff_id");

        System.out.println(map);
        System.out.println(staff_id);
        int res=-1;
        if(staff_id==null ||staff_id==""){
            res= mapper.addDesc(map);
        }else{
            res= mapper.updateDesc(map);

        }

        System.out.println(res);
        return res;
    }

    // 假删除员工  5
    @Override
    public boolean deleteDesc(int staff_id) {
        int i = mapper.deleteDesc(staff_id);
        return i!=-1?true:false;
    }


    /*下拉列表*/

    //部门 6
    @Override
    public List<Map<String, Object>> getDept() {
        List<Map<String, Object>> bumen = mapper.getDept();
        List<Map<String, Object>> list = util.getcombobox(bumen);
        return list;
    }

    //角色 7
    @Override
    public List<Map<String, Object>> getRole() {
        List<Map<String, Object>> juese = mapper.getRole();
        List<Map<String, Object>> list = util.getcombobox(juese);
        return list;
    }
}
