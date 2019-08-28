package com.oaec.serviceImpl;

import com.oaec.dao.AuthMapper;
import com.oaec.service.AuthService;
import com.oaec.util.ModuleUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {
    @Resource
    private ModuleUtil util;

      @Resource
    private AuthMapper mapper;

      //1.查询所有权限
    @Override
    public List<Map<String, Object>> getAllAuth() {
        List<Map<String, Object>> allAuth = mapper.getAllAuth();
        List<Map<String, Object>> list = util.moduleUtil(allAuth);
        return list;
    }

    //2. 添加或修改权限
    @Override
    public int saveOrUpdateAuth(Map<String, Object> map) {
        Object auth_id = map.get("auth_id");
        int res=-1;
        if(auth_id==null ||auth_id==""){
           res= mapper.addAuth(map);
        }else{
            res= mapper.updateAuth(map);
        }
        return res;
    }
}
