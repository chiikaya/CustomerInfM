package com.oaec.serviceImpl;

import com.oaec.dao.ClientMapper;
import com.oaec.service.ClientService;
import com.oaec.util.ModuleUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClientServiceImpl implements ClientService {

    @Resource
    private ModuleUtil util;

    @Resource
    private ClientMapper mapper;


    //查询数据  1+2
    @Override
    public Map<String, Object> findAllClient(Map<String, Object> map) {

        String find_text = (String)map.get("find_text");
        String find_where = (String)map.get("find_where");

        if (find_text != null && find_where != null) {
            if (("客户状态").equals(find_where)) {
                map.put("byStatus", find_text);
            }
            if (("客户来源").equals(find_where)) {
                map.put("byFrom", find_text);
            }
            if (("客户类型").equals(find_where)) {
                map.put("byType", find_text);
            }
            if (("客户姓名").equals(find_where)) {
                map.put("byName", find_text);
            }
            if (("所属员工").equals(find_where)) {
                map.put("byStaff", find_text);
            }
            if (("公司").equals(find_where)) {
                map.put("byCompany", find_text);
            }
        }
        //页面
        int pageNo = Integer.parseInt(map.get("pageNo").toString());
        int pageSize = Integer.parseInt(map.get("pageSize").toString());
        //起始下标
        int startIndex = (pageNo - 1) * pageSize; // limit 0,10
        map.put("startIndex", startIndex);
        //总条数
        int totalCount = mapper.findClientCount(map);
        List<Map<String, Object>> allClient = mapper.findAllClient(map);

        //封装成对应的json格式
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", totalCount);
        result.put("rows", allClient);
        return result;
    }

    // 添加或修改客户信息   3+4
    @Override
    public int saveOrUpdateClient(Map<String, Object> map) {
        Object client_id = map.get("client_id");

       // System.out.println(map);
        //System.out.println(client_id);
        int res;
        if(client_id==null ||client_id==""){
            res= mapper.addClient(map);
        }else{
            res= mapper.updateClient(map);
        }

        System.out.println(res);
        return res;
    }

    // 假删除客户
    @Override
    public boolean deleteClient(int client_id) {
        int i = mapper.deleteClient(client_id);
        return (i!=-1)?true:false;
    }

    //客户状态
    @Override
    public List<Map<String, Object>> getStatus() {
        List<Map<String, Object>> khztlist = mapper.getStatus();
        List<Map<String, Object>> list = util.getcombobox(khztlist);
        return list;
    }

    //客户来源
    @Override
    public List<Map<String, Object>> getFrom() {
        List<Map<String, Object>> khly  = mapper.getFrom();
        List<Map<String, Object>> list = util.getcombobox(khly);
        return list;
    }

    //所属员工
    @Override
    public List<Map<String, Object>> getStaff() {
        List<Map<String, Object>> yg  = mapper.getStaff();
        List<Map<String, Object>> list = util.getcombobox(yg);
        return list;
    }

    //客户类型
    @Override
    public List<Map<String, Object>> getMold() {
        List<Map<String, Object>> khlx  = mapper.getMold();
        List<Map<String, Object>> list = util.getcombobox(khlx);
        return list;
   }


}