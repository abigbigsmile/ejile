package com.qtztlink.ejile.common.controller;

import com.qtztlink.ejile.common.bean.SysLog;
import com.qtztlink.ejile.common.dao.SysLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class LogController {

    @Autowired
    private SysLogDao sysLogDao;

    @RequestMapping("/log/all")
    @ResponseBody
    public List<Map<String, Object>> getAllLogs() {
//        List<Map<String, Object>> res = this.sysLogDao.queryAllLogs();
//        for(Map<String, Object> map : res){
//            //SysLog sl =
//            //String dateTime = map.get("cre")
//        }
        return this.sysLogDao.queryAllLogs();
    }
}
