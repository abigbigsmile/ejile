package com.qtztlink.ejile.system.service.log.impl;

import com.qtztlink.ejile.common.bean.SysLog;
import com.qtztlink.ejile.common.dao.SysLogDao;
import com.qtztlink.ejile.system.service.log.LogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Tiger
 * @date 2019-12-05
 * @see com.qtztlink.ejile.system.service.log.impl
 **/
@Service
public class LogServiceImpl implements LogService {
    @Resource
    private SysLogDao sysLogDao;

    @Override
    public List<SysLog> findAll() {
        return sysLogDao.findAll();
    }

    @Override
    public Page<SysLog> findByCondition(String keyword, String[] choseTime, Pageable pageable) throws ParseException {

        return sysLogDao.findAll((root, query, cb) -> {
            List<Predicate> list = new ArrayList<>();
            if (keyword != null) {
                list.add(cb.like(root.get("operation"), keyword));
            }
            if (choseTime != null) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String start = choseTime[0];
                String end = choseTime[1];
                Date startTime = null;
                Date endTime = null;
                try {
                    startTime = (start == null) ? null : dateFormat.parse(start);
                    endTime = (end == null) ? null : dateFormat.parse(end);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (startTime != null && endTime != null) {
                    list.add(cb.greaterThanOrEqualTo(root.get("createTime"), startTime));
                    list.add(cb.lessThanOrEqualTo(root.get("createTime"), endTime));
                }
            }
            Predicate[] p = new Predicate[list.size()];
            return cb.and(list.toArray(p));
        }, pageable);
    }
}
