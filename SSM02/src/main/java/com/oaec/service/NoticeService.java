package com.oaec.service;

import java.util.List;
import java.util.Map;

public interface NoticeService {
    //查询数据
    Map<String,Object> findAllNotice(Map<String,Object> map);  // 1+2

    // 添加或修改
    int saveOrUpdateNotice(Map<String,Object> map);   // 3+4

    //删除
    boolean deleteNotice(int notice_id);    //5




    /*                        下拉列表           */
    //员工姓名
    public List<Map<String,Object>> getDESC();
}
