package com.oaec.serviceImpl;

import com.oaec.dao.PersonMapper;
import com.oaec.service.PersonService;
import com.oaec.util.ModuleUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PersonServiceImpl implements PersonService {
    @Resource
    private ModuleUtil util;

    @Resource
    private PersonMapper mapper;

    //查询数据  1+2
    @Override
    public Map<String, Object> findAllPerson(Map<String, Object> map) {

        String person_text = (String)map.get("person_text");
        String person_way = (String)map.get("person_way");

        if (person_text != null && person_way != null) {
            if (("联系人姓名").equals(person_way)) {
                map.put("byPEName", person_text);
            }
            if (("属于的客户").equals(person_way)) {
                map.put("byPEKH", person_text);
            }

        }

        //页码
        int pageNo =Integer.parseInt(map.get("pageNo").toString());
        int pageSize=Integer.parseInt(map.get("pageSize").toString());
        //起始下标
        int startIndex = (pageNo-1)*pageSize; // limit 0,10
        map.put("startIndex",startIndex);
        //总条数
        int totalCount = mapper.findPersonCount(map);
        List<Map<String, Object>> allPerson = mapper.findAllPerson(map);

        //封装成对应的json格式
        Map<String,Object> result = new HashMap<>();
        result.put("total",totalCount);
        result.put("rows",allPerson);

        return result;
    }

    // 添加或修改联系人   (3+4)
    @Override
    public int saveOrUpdatePerson(Map<String, Object> map) {
        Object person_id = map.get("person_id");
        int res=-1;
        if(person_id==null ||person_id==""){
            res= mapper.addPerson(map);
        }else{
            res= mapper.updatePerson(map);
        }
        return res;
    }

    //删除联系人 5
    @Override
    public boolean deletePerson(int person_id) {
        int res = mapper.deletePerson(person_id);
        return res == 1 ? true : false;
    }



    /*下拉列表*/
    //客户   6
    @Override
    public List<Map<String, Object>> getPersonKH() {
        List<Map<String, Object>> kh2 = mapper.getPersonKH();
        List<Map<String, Object>> list = util.getcombobox(kh2);
        return list;
    }
}
