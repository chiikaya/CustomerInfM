package com.oaec.dao;

import java.util.List;
import java.util.Map;

public interface AllotMapper {

    //1. 查询总条数
    int findAllotCount(Map<String, Object> map);

    //2. 查询数据
    List<Map<String,Object>> findAllAllot(Map<String, Object> map);
}
