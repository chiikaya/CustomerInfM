package com.oaec.serviceImpl;

import com.oaec.dao.NoticeMapper;
import com.oaec.service.NoticeService;
import com.oaec.util.ModuleUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {
    @Resource
    private ModuleUtil util;

    @Resource
    private NoticeMapper mapper;

    //查询数据  1+2
    @Override
    public Map<String, Object> findAllNotice(Map<String, Object> map)  {
        //下拉列表搜索
        String notice_text = (String)map.get("notice_text");
        String notice_way = (String)map.get("notice_way");

        if (notice_text != null && notice_way != null) {
            if (("公告主题").equals(notice_way)) {
                map.put("Text", notice_text);
            }
            if (("公告内容").equals(notice_way)) {
                map.put("Content", notice_text);
            }

        }
        //页码
        int pageNo =Integer.parseInt(map.get("pageNo").toString());
        int pageSize=Integer.parseInt(map.get("pageSize").toString());
        //起始下标
        int startIndex = (pageNo-1)*pageSize; // limit 0,10
        map.put("startIndex",startIndex);
        //总条数
        int totalCount = mapper.findNoticeCount(map);
        List<Map<String, Object>> allRole = mapper.findAllNotice(map);


        //格式化日期
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        for(Map<String, Object> map1:allRole){
            Object create_time = map1.get("create_time");
            Object reply_time = map1.get("reply_time");


            Date d =null;
            Date d2 =null;
            if(create_time instanceof  Date){
                d = (Date)create_time;
            }
            if(reply_time instanceof Date){
                d2 = (Date)reply_time;
            }

            String format = sdf1.format(d);
            String format2 = sdf1.format(d2);
            map1.put("create_time",format);
            map1.put("reply_time",format2);
        }

        //封装成对应的json格式
        Map<String,Object> result = new HashMap<>();
        result.put("total",totalCount);
        result.put("rows",allRole);

        return result;
    }

    // 添加或修改   (3+4)
    @Override
    public int saveOrUpdateNotice(Map<String, Object> map) {
        Object notice_id = map.get("notice_id");
        int res=-1;
        if(notice_id==null ||notice_id==""){
            res= mapper.addNotice(map);
        }else{
            res= mapper.updateNotice(map);
        }
        return res;
    }



    //删除客户   5
    @Override
    public boolean deleteNotice(int notice_id) {
        int res = mapper.deleteNotice(notice_id);
        return res == 1 ? true : false;
    }




    /*下拉列表*/
    //员工姓名
    @Override
    public List<Map<String, Object>> getDESC() {
        List<Map<String, Object>> yuangong = mapper.getDESC();
        List<Map<String, Object>> list = util.getcombobox(yuangong);
        return list;
    }
}
