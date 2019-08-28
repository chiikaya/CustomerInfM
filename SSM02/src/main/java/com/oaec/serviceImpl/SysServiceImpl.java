package com.oaec.serviceImpl;

import com.oaec.dao.SysMapper;
import com.oaec.service.SysService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class SysServiceImpl implements SysService {
    @Resource
    private SysMapper mapper;

    //1. 用户登录
    @Override
    public List<Map<String, Object>> userLogin(String username,String password) {
        Map<String,Object> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        return mapper.userLogin(map);
    }

    //2.根据用户id查询用户所有的权限
    @Override
    public List<Map<String, Object>> getAllAuthByUserId(int user_id) {
        return mapper.getAllAuthByUserId(user_id);
    }

    //3.根据用户id查询所有的资源权限
    @Override
    public List<Map<String, Object>> getAllResourceByUserId(int user_id) {
        return mapper.getAllResourceByUserId(user_id);
    }
}
