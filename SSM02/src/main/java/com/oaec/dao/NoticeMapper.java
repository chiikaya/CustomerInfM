package com.oaec.dao;

import java.util.List;
import java.util.Map;

public interface NoticeMapper {
    //1. 查询总条数
    int findNoticeCount(Map<String,Object> map);

    //2. 查询数据
    List<Map<String,Object>> findAllNotice(Map<String,Object> map);

    //3. 添加
    int addNotice(Map<String, Object> map);

    //4. 修改
    int updateNotice(Map<String, Object> map);

    //5. 删除客户
    int deleteNotice(int notice_id);


    /*               下拉列表                          */
    //员工姓名
    public List<Map<String,Object>> getDESC();
}
