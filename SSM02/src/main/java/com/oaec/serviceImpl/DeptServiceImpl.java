package com.oaec.serviceImpl;

import com.oaec.dao.DeptMapper;
import com.oaec.service.DeptService;
import com.oaec.util.ModuleUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DeptServiceImpl implements DeptService {

    @Resource
    private ModuleUtil util;

    @Resource
    private DeptMapper mapper;

    //查询数据  1+2
    @Override
    public Map<String, Object> findAllDept(Map<String, Object> map) {
        //页码
        int pageNo =Integer.parseInt(map.get("pageNo").toString());
        int pageSize=Integer.parseInt(map.get("pageSize").toString());
        //起始下标
        int startIndex = (pageNo-1)*pageSize; // limit 0,10
        map.put("startIndex",startIndex);
        //总条数
        int totalCount = mapper.findDeptCount(map);
        List<Map<String, Object>> allDept = mapper.findAllDept(map);
        //封装成对应的json格式
        Map<String,Object> result = new HashMap<>();
        result.put("total",totalCount);
        result.put("rows",allDept);

        return result;
    }

    // 添加或修改部门   3+4
    @Override
    public int saveOrUpdateDept(Map<String, Object> map) {
        Object dept_id = map.get("dept_id");
        int res=-1;
        if(dept_id==null ||dept_id==""){
            res= mapper.addDept(map);
        }else{
            res= mapper.updateDept(map);
        }
        return res;
    }

    // 删除部门    5+6+7
    @Override
    public int deleteDept(int dept_id) {
        int i = mapper.deleteDept(dept_id);
        List<Integer> list = mapper.findUser4DeleteDept(dept_id);
        if(list.size()>0){
            int i1 = mapper.updateUserDept(list);
        }
        return 1;
    }

}
