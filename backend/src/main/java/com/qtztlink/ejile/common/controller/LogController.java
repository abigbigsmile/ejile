package com.qtztlink.ejile.common.controller;

import com.qtztlink.ejile.common.bean.ResponseBean;
import com.qtztlink.ejile.common.bean.SysLog;
import com.qtztlink.ejile.common.dao.SysLogDao;
import com.qtztlink.ejile.system.service.log.LogService;
import io.lettuce.core.GeoArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
public class LogController {

    @Resource
    private LogService logService;

    @GetMapping("/log")
    public ResponseBean getLogs(@RequestParam(name = "page") int page,
                                @RequestParam(name = "size") int size,
                                @RequestParam(name = "keyword", required = false) String keyword,
                                @RequestParam(name = "choseTime", required = false) String[] choseTime) throws ParseException {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(page-1, size, sort);
        return new ResponseBean().code(200).message("ok").data(logService.findByCondition(keyword, choseTime, pageable));
//        return new ResponseBean().code(200).message("ok").data(logService.findAll());
    }
}
