package com.oaec.dao;

import java.util.List;
import java.util.Map;

//房屋信息
public interface TypeMapper {
    //1. 查询总条数
    int findTypeCount(Map<String, Object> map);

    //2. 查询数据
    List<Map<String,Object>> findAllType(Map<String, Object> map);

    //3. 添加房屋类型
    int addType(Map<String,Object> map);

    //4. 修改房屋类型
    int updateType(Map<String,Object> map);

    //5. 假删除
    int deleteType(int type_id);
}
