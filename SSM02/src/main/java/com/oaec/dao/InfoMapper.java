package com.oaec.dao;

import java.util.List;
import java.util.Map;

//房屋信息

public interface InfoMapper {
    //1. 查询总条数
    int findInfoCount(Map<String, Object> map);

    //2. 查询数据
    List<Map<String,Object>> findAllInfo(Map<String, Object> map);

    //3. 添加房屋信息
    int addInfo(Map<String,Object> map);

    //4. 修改房屋信息
    int updateInfo(Map<String,Object> map);

    //5. 假删除
    int deleteInfo(int info_id);



    //6.户型
    public List<Map<String,Object>> getType();

    //7.管理员
    public List<Map<String,Object>> getDesc2();
}
