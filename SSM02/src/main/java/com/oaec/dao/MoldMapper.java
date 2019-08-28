package com.oaec.dao;

import java.util.List;
import java.util.Map;

public interface MoldMapper {
    //1. 查询总条数
    int findMoldCount(Map<String, Object> map);

    //2. 查询数据
    List<Map<String,Object>> findAllMold(Map<String, Object> map);

    //3. 添加客户类型
    int addMold(Map<String,Object> map);

    //4. 修改客户类型
    int updateMold(Map<String,Object> map);

    //5. 假删
    int deleteMold(int mold_id);
}
