package com.oaec.dao;

import java.util.List;
import java.util.Map;

public interface FromMapper {
    //1. 查询总条数
    int findFromCount(Map<String, Object> map);

    //2. 查询数据
    List<Map<String,Object>> findAllFrom(Map<String, Object> map);

    //3. 添加客户来源
    int addFrom(Map<String,Object> map);

    //4. 修改客户来源
    int updateFrom(Map<String,Object> map);

    //5. 假删
    int deleteFrom(int from_id);


}
